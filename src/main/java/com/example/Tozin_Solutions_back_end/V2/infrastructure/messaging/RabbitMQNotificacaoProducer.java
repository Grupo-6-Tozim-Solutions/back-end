package com.example.Tozin_Solutions_back_end.V2.infrastructure.messaging;

import com.example.Tozin_Solutions_back_end.V2.core.domain.NotificacaoDomain.Notificacao;
import com.example.Tozin_Solutions_back_end.V2.infrastructure.config.RabbitMqConfig;
import com.example.Tozin_Solutions_back_end.V2.infrastructure.persistence.NotificacaoPersistence.jpa.NotificacaoQueueGateway;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQNotificacaoProducer implements NotificacaoQueueGateway {
    private final RabbitTemplate rabbitTemplate;

    public RabbitMQNotificacaoProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void enviarParaFila(Notificacao notificacao) {
        rabbitTemplate.convertAndSend(
                RabbitMqConfig.EXCHANGE_NAME,
                "notificacoes.rk",
                notificacao.getMensagem()
        );
        System.out.println("📤 Mensagem enviada para RabbitMQ: " + notificacao.getMensagem());
    }

    public void enviarEmailBoasVindas(String email, String nome) {
        String mensagem = email + ";" + nome;
        rabbitTemplate.convertAndSend(
                RabbitMqConfig.EMAIL_EXCHANGE,
                "emails.boasvindas",
                mensagem
        );
        System.out.println("📤 Mensagem enviada à fila de e-mails: " + mensagem);
    }
}
