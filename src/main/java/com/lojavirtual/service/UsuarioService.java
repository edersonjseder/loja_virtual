package com.lojavirtual.service;

import com.lojavirtual.exception.AcessoNaoEncontradoException;
import com.lojavirtual.exception.PessoaException;
import com.lojavirtual.exception.UsuarioNaoEncontradoException;
import com.lojavirtual.model.*;
import com.lojavirtual.repository.*;
import com.lojavirtual.response.UserTokenResponse;
import com.lojavirtual.security.request.SignInRequest;
import com.lojavirtual.security.request.SignUpRequest;
import com.lojavirtual.response.TokenResponse;
import com.lojavirtual.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.GregorianCalendar;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final PessoaRepository pessoaRepository;
    private final AcessoRepository acessoRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final DateUtils dateUtils;

    public UserTokenResponse registrarUsuario(Pessoa pessoa) {
        var usuario = usuarioRepository.save(Usuario.builder()
                .login(pessoa.getEmail())
                .senha(passwordEncoder.encode("12345678"))
                .dataAtualSenha(GregorianCalendar.getInstance().getTime())
                .acessos(List.of(verificarAcesso("ROLE_BASIC")))
                .pessoa(pessoa)
                .build());

        var jwt = jwtService.generateToken(usuario);

        return UserTokenResponse.builder()
                .username(usuario.getLogin())
                .dataAtualSenha(dateUtils.parseDate(usuario.getDataAtualSenha()))
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
