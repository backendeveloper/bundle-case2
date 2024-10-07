package com.bundle.dbwriter.listener;

import com.bundle.dbwriter.model.MessageRecord;
import com.bundle.dbwriter.service.MessageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQListener {

    private static final Logger logger = LoggerFactory.getLogger(RabbitMQListener.class);
    private final ObjectMapper objectMapper;
    private final MessageService messageService;

    public RabbitMQListener(ObjectMapper objectMapper, MessageService messageService) {
        this.objectMapper = objectMapper;
        this.messageService = messageService;
    }

    @RabbitListener(queues = "${app.rabbitmq.queue}")
    public void receiveMessage(String message) {
        logger.info("Received message from RabbitMQ: {}", message);
        try {
            MessageRecord dataRecord = objectMapper.readValue(message, MessageRecord.class);
            messageService.saveDataRecord(dataRecord);
        } catch (Exception e) {
            logger.error("Failed to process message: {}", message, e);
        }
    }
}
