package com.example.Tozin_Solutions_back_end.V2.infrastructure.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
    public static final String QUEUE_NAME = "notificacoes";
    public static final String EXCHANGE_NAME = "notificacoes-exchange";
    public static final String EMAIL_QUEUE = "emails";
    public static final String EMAIL_EXCHANGE = "emails-exchange";

    @Bean
    public Queue notificacoesQueue() {
        return new Queue(QUEUE_NAME, true);
    }

    @Bean
    public TopicExchange notificacoesExchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Binding binding1(Queue notificacoesQueue, TopicExchange notificacoesExchange) {
        return BindingBuilder.bind(notificacoesQueue)
                .to(notificacoesExchange)
                .with("notificacoes.#");
    }

    @Bean
    public Queue emailQueue() {
        return new Queue(EMAIL_QUEUE, true);
    }

    @Bean
    public TopicExchange emailExchange() {
        return new TopicExchange(EMAIL_EXCHANGE);
    }

    @Bean
    public Binding binding(Queue emailQueue, TopicExchange emailExchange) {
        return BindingBuilder.bind(emailQueue).to(emailExchange).with("emails.#");
    }
}
