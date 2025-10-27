package com.example.Tozin_Solutions_back_end.integracao;

import com.example.Tozin_Solutions_back_end.V2.core.domain.NotificacaoDomain.Notificacao;
import com.example.Tozin_Solutions_back_end.V2.infrastructure.messaging.RabbitMQNotificacaoProducer;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class RabbitMQIntegrationTest {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private RabbitMQNotificacaoProducer producer;

    @Test
    void deveEnviarMensagemParaRabbitMQLocal() {
        Notificacao notificacao = new Notificacao();
        notificacao.setDestinatario("lucas@teste.com");
        notificacao.setMensagem("Mensagem enviada para RabbitMQ local");

        producer.enviarParaFila(notificacao);

        Object mensagem = rabbitTemplate.receiveAndConvert("notificacoes");
        assertThat(mensagem).isNotNull();
    }
}
