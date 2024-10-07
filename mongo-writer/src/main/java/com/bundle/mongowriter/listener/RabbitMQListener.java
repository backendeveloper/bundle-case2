package com.bundle.mongowriter.listener;

import com.bundle.mongowriter.model.MessageRecord;
import com.bundle.mongowriter.strategy.IMessageProcessingStrategy;
import com.bundle.mongowriter.strategy.MessageProcessingStrategySelector;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQListener {

    private static final Logger logger = LoggerFactory.getLogger(RabbitMQListener.class);
    private final ObjectMapper objectMapper;
    private final MessageProcessingStrategySelector strategySelector;

    public RabbitMQListener(ObjectMapper objectMapper, MessageProcessingStrategySelector strategySelector) {
        this.objectMapper = objectMapper;
        this.strategySelector = strategySelector;
    }

    @RabbitListener(queues = "${app.rabbitmq.queue}")
    public void receiveMessage(String message) {
        logger.info("Received message from RabbitMQ: {}", message);
        try {
            MessageRecord dataRecord = objectMapper.readValue(message, MessageRecord.class);
            logger.info("Deserialized MessageRecord: {}", dataRecord);
            IMessageProcessingStrategy strategy = strategySelector.selectStrategy(dataRecord);
            strategy.process(dataRecord);
        } catch (Exception e) {
            logger.error("Failed to process message: {}", message, e);
        }
    }
}
