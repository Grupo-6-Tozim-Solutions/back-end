package com.example.Tozin_Solutions_back_end.V2.infrastructure.messaging.producer;

import com.example.Tozin_Solutions_back_end.V2.core.domain.MessasingDomain.MensageriaGateway;
import com.example.Tozin_Solutions_back_end.V2.infrastructure.messaging.config.RabbitMqConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqMensagemProducer implements MensageriaGateway {
    private final RabbitTemplate rabbitTemplate;

    public RabbitMqMensagemProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void enviarMensagem(String mensagem) {
        rabbitTemplate.convertAndSend(RabbitMqConfig.FILA_TESTE, mensagem);
        System.out.println("Mensagem enviada: " + mensagem);
    }
}
