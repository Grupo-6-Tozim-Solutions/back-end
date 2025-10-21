package com.example.Tozin_Solutions_back_end.V2.infrastructure.messaging.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
    public static final String FILA_TESTE = "fila-teste";

    @Bean
    public Queue filaTeste() {
        return new Queue(FILA_TESTE, false);
    }
}
