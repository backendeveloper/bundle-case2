//package com.bundle.socketlistener.service;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.stereotype.Service;
//
//@Service
//public class MessageQueueSenderService implements IMessageQueueSenderService {
//    private static final Logger logger = LoggerFactory.getLogger(MessageQueueSenderService.class);
//    private final RabbitTemplate rabbitTemplate;
//    private final ObjectMapper objectMapper;
//
//    public MessageQueueSenderService(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper) {
//        this.rabbitTemplate = rabbitTemplate;
//        this.objectMapper = objectMapper;
//    }
//
//    @Override
//    public void sendMessage(String routingKey, Object message) {
//        try {
//            String jsonMessage = objectMapper.writeValueAsString(message);
//            rabbitTemplate.convertAndSend(routingKey, jsonMessage);
//            logger.info("Message sent to RabbitMQ with routing key '{}': {}", routingKey, jsonMessage);
//        } catch (JsonProcessingException e) {
//            logger.error("Failed to convert message to JSON", e);
//            throw new RuntimeException("Message conversion failed", e);
//        } catch (Exception e) {
//            logger.error("Failed to send message to RabbitMQ", e);
//            throw new RuntimeException("Message sending failed", e);
//        }
//    }
//}
