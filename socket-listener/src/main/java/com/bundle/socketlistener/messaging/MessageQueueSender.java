package com.bundle.socketlistener.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageQueueSender implements IMessageQueueSender {
    private static final Logger logger = LoggerFactory.getLogger(MessageQueueSender.class);
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    public MessageQueueSender(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public void sendMessage(String routingKey, Object message) {
        try {
            String jsonMessage = objectMapper.writeValueAsString(message);
            Message msg = MessageBuilder.withBody(jsonMessage.getBytes())
                    .setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN)
                    .setDeliveryMode(MessageDeliveryMode.PERSISTENT)
                    .build();
            rabbitTemplate.send("datagenerator.exchange", routingKey, msg);

            logger.info("Message sent to RabbitMQ with routing key '{}': {}", routingKey, jsonMessage);
        } catch (JsonProcessingException e) {
            logger.error("Failed to convert message to JSON", e);
            throw new RuntimeException("Message conversion failed", e);
        } catch (Exception e) {
            logger.error("Failed to send message to RabbitMQ", e);
            throw new RuntimeException("Message sending failed", e);
        }

        logger.info("Sending message to queue: {}", message);
    }
}
