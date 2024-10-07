package com.bundle.dbwriter.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${app.rabbitmq.exchange}")
    private String exchangeName;

    @Value("${app.rabbitmq.queue}")
    private String queueName;

    @Value("${app.rabbitmq.routing-key}")
    private String routingKey;

    @Bean
    public TopicExchange datageneratorExchange() {
        return new TopicExchange(exchangeName);
    }

    @Bean
    public Queue datageneratorQueue() {
        return new Queue(queueName, true);
    }

    @Bean
    public Binding binding(Queue datageneratorQueue, TopicExchange datageneratorExchange) {
        return BindingBuilder.bind(datageneratorQueue).to(datageneratorExchange).with(routingKey);
    }
}
