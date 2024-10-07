package com.bundle.socketlistener.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${app.rabbitmq.exchange}")
    private String exchange;

    @Value("${app.rabbitmq.queue}")
    private String queueName;

    @Value("${app.rabbitmq.routing-key}")
    private String routingKey;

    @Bean
    public Exchange datageneratorExchange() {
        return ExchangeBuilder.topicExchange(exchange).durable(true).build();
    }

    @Bean
    public Queue datageneratorQueue() {
        return QueueBuilder.durable(queueName).build();
    }

    @Bean
    public Binding binding(Queue datageneratorQueue, Exchange datageneratorExchange) {
        return BindingBuilder.bind(datageneratorQueue).to(datageneratorExchange).with(routingKey).noargs();
    }
}
