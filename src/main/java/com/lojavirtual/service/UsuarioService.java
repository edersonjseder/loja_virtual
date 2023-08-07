package com.lojavirtual.service;

import com.lojavirtual.exception.AcessoNaoEncontradoException;
import com.lojavirtual.exception.PessoaException;
import com.lojavirtual.exception.UsuarioNaoEncontradoException;
import com.lojavirtual.model.Acesso;
import com.lojavirtual.model.EmailModel;
import com.lojavirtual.model.Pessoa;
import com.lojavirtual.model.Usuario;
import com.lojavirtual.repository.AcessoRepository;
import com.lojavirtual.repository.PessoaRepository;
import com.lojavirtual.repository.UsuarioRepository;
import com.lojavirtual.response.TokenResponse;
import com.lojavirtual.response.UserTokenResponse;
import com.lojavirtual.security.request.SignInRequest;
import com.lojavirtual.security.request.SignUpRequest;
import com.lojavirtual.utils.DateUtils;
import com.lojavirtual.utils.MailHtml;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.util.GregorianCalendar;
import java.util.List;

import static com.lojavirtual.constants.DBConstants.DROP_CONSTRAINT_USUARIO;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    @Value("${mail.smtp.username}")
    private String username;
    private static final String SUBJECT = "Acesso gerado para o sistema";

    private final UsuarioRepository usuarioRepository;
    private final PessoaRepository pessoaRepository;
    private final AcessoRepository acessoRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final DateUtils dateUtils;
    private final JdbcTemplate jdbcTemplate;
    private final SendEmailService sendEmailService;

    public UserTokenResponse registrarUsuario(Pessoa pessoa) {
        var retrievedUsuario = usuarioRepository.findUserByPessoa(pessoa.getId(), pessoa.getEmail());
        MailHtml mailHtml = new MailHtml();

        if (retrievedUsuario == null) {
            String constraint = usuarioRepository.consultaConstraintAcesso();

            if (constraint != null) {
                jdbcTemplate.execute(DROP_CONSTRAINT_USUARIO + constraint + "; commit;");
            }

            retrievedUsuario = usuarioRepository.save(Usuario.builder()
                    .login(pessoa.getEmail())
                    .senha(passwordEncoder.encode("12345678"))
                    .dataAtualSenha(GregorianCalendar.getInstance().getTime())
                    .acessos(List.of(verificarAcesso("ROLE_BASIC")))
                    .pessoa(pessoa)
                    .empresa(pessoa.getEmpresa())
                    .build());
        }

        var jwt = jwtService.generateToken(retrievedUsuario);

        var email = EmailModel.builder()
                .sentFrom(username)
                .sendTo(pessoa.getEmail())
                .subject(SUBJECT)
                .message(mailHtml.formatEmail(pessoa.getNome(), pessoa.getEmail(), "12345678"))
                .build();

        try {
            sendEmailService.enviarEmail(email);
        } catch (MessagingException | UnsupportedEncodingException e) {
            e.getStackTrace();
        }

        return UserTokenResponse.builder()
                .username(retrievedUsuario.getLogin())
                .dataAtualSenha(dateUtils.parseDate(retrievedUsuario.getDataAtualSenha()))
                .authorization(jwt).build();
    }

    @Transactional
    public TokenResponse signUp(SignUpRequest request) {
        var usuario = new Usuario();
        usuario.setLogin(request.getUsername());
        usuario.setSenha(passwordEncoder.encode(request.getPassword()));
        usuario.setDataAtualSenha(GregorianCalendar.getInstance().getTime());
        usuario.setAcessos(List.of(verificarAcesso(request.getRole())));
        usuario.setPessoa(verificarTipoPessoa(request));

        usuario = usuarioRepository.save(usuario);

        var jwt = jwtService.generateToken(usuario);

        return TokenResponse.builder().authorization(jwt).build();
    }


    public TokenResponse signIn(SignInRequest request) throws BadCredentialsException {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        var user = usuarioRepository.findUserByLogin(request.getUsername())
                .orElseThrow(() -> new UsuarioNaoEncontradoException(request.getUsername()));

        var jwt = jwtService.generateToken(user);

        return TokenResponse.builder().authorization(jwt).build();
    }

    private Acesso verificarAcesso(String acesso) {
        return acessoRepository.carregarAcessoPorDescricao(acesso).orElseThrow(() -> new AcessoNaoEncontradoException(acesso));
    }

    private Pessoa verificarTipoPessoa(SignUpRequest request) {
        if (request.getTipo().equalsIgnoreCase("PF")) {
            return pessoaRepository
                    .buscarPessoaFisicaPorEmail(request.getEmail())
                    .orElseThrow(() -> new PessoaException("Pessoa nao encontrada!"));
        } else if (request.getTipo().equalsIgnoreCase("PJ")) {
            return pessoaRepository
                    .buscarPessoaJuridicaPorEmail(request.getEmail())
                    .orElseThrow(() -> new PessoaException("Pessoa nao encontrada!"));
        } else {
            throw new PessoaException("Pessoa Inexistente");
        }
    }
}
