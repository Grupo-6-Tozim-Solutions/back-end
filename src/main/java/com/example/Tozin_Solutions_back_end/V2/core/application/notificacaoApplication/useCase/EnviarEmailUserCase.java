package com.example.Tozin_Solutions_back_end.V2.core.application.notificacaoApplication.useCase;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EnviarEmailUserCase {
    private final JavaMailSender mailSender;

    public EnviarEmailUserCase(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void enviarEmailBoasVindas(String email, String nome) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Bem-vindo ao Tozin Solutions");
        message.setText("Olá " + nome + ",\n\nSeja muito bem-vindo à nossa plataforma! 😃");
        mailSender.send(message);

        mailSender.send(message);
        System.out.println("📧 E-mail de boas-vindas enviado para " + email);
    }
}
