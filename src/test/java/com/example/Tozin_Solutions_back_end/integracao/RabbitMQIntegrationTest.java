package com.example.Tozin_Solutions_back_end.integracao;

import com.example.Tozin_Solutions_back_end.TestConfig;
import com.example.Tozin_Solutions_back_end.V2.core.domain.NotificacaoDomain.Notificacao;
import com.example.Tozin_Solutions_back_end.V2.infrastructure.messaging.RabbitMQNotificacaoProducer;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ActiveProfiles("test")
@Import(TestConfig.class)
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

        // Verifica que o produtor foi chamado
        producer.enviarParaFila(notificacao);

        // Verifica que o RabbitTemplate.convertAndSend (exchange, routingKey, message)
        // foi chamado
        verify(rabbitTemplate).convertAndSend(anyString(), anyString(), any(Object.class));
    }
}
