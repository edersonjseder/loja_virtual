package com.lojavirtual.service;

import com.lojavirtual.model.EmailModel;
import com.lojavirtual.repository.UsuarioRepository;
import com.lojavirtual.utils.MailHtml;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service
@RequiredArgsConstructor
public class TarefaAutomatizadaService {

    @Value("${mail.smtp.username}")
    private String username;
    private static final String SUBJECT = "Senha expirada";

    private final UsuarioRepository usuarioRepository;
    private final SendEmailService sendEmailService;

    @Scheduled(initialDelay = 2000, fixedDelay = 86400000)
    public void notificarUsuarioTrocarSenha() {
        MailHtml mailHtml = new MailHtml();
        var usuarios = usuarioRepository.verificarUsuarioSenhaVencida();

        usuarios.forEach(usuario -> {
            try {
                sendEmailService.enviarEmail(EmailModel.builder()
                                .sentFrom(username)
                                .sendTo(usuario.getPessoa().getEmail())
                                .subject(SUBJECT)
                                .message(mailHtml.formatSenhaVencidaHtml(usuario.getPessoa().getNome()))
                                .build());
                Thread.sleep(3000);
            } catch (MessagingException | UnsupportedEncodingException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
