package com.example.Tozin_Solutions_back_end.V2.infrastructure.messaging;

import com.example.Tozin_Solutions_back_end.V2.core.application.notificacaoApplication.useCase.EnviarEmailUserCase;
import com.example.Tozin_Solutions_back_end.V2.core.domain.NotificacaoDomain.Notificacao;
import com.example.Tozin_Solutions_back_end.V2.core.domain.usuarioDomain.valueObjects.Email;
import com.example.Tozin_Solutions_back_end.V2.infrastructure.config.RabbitMqConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQNotificacaoConsumer {

    private final EnviarEmailUserCase enviarEmail;

    public RabbitMQNotificacaoConsumer(EnviarEmailUserCase enviarEmail) {
        this.enviarEmail = enviarEmail;
    }

    @RabbitListener(queues = RabbitMqConfig.QUEUE_NAME)
    public void consumir(Notificacao notificacao) {
        System.out.println("📩 Notificação recebida: " + notificacao.getMensagem());
    }

    @RabbitListener(queues = RabbitMqConfig.EMAIL_QUEUE)
    public void processarMensagem(String mensagem) {
        String[] partes = mensagem.split(";");
        if (partes.length >= 2) {
            String email = partes[0].trim();
            String nome = partes[1].replace("Novo usuário cadastrado: ", "").trim();


            if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
                System.err.println("❌ E-mail inválido: " + email);
                return;
            }

            enviarEmail.enviarEmailBoasVindas(email, nome);
        }
    }
}
