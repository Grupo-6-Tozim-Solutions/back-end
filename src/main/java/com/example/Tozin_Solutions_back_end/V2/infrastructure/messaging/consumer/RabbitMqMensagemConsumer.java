package com.example.Tozin_Solutions_back_end.V2.infrastructure.messaging.consumer;

import com.example.Tozin_Solutions_back_end.V2.infrastructure.messaging.config.RabbitMqConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqMensagemConsumer {
    @RabbitListener(queues = RabbitMqConfig.FILA_TESTE)
    public void receberMensagem(String mensagem) {
        System.out.println("Mensagem recebida: " + mensagem);
    }
}
