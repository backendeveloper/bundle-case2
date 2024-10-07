package com.bundle.socketlistener.processor;

import com.bundle.socketlistener.messaging.MessageQueueSender;
import com.bundle.socketlistener.model.DataRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class QueueMessageProcessor implements IMessageProcessor {
    private static final Logger logger = LoggerFactory.getLogger(QueueMessageProcessor.class);
    private final MessageQueueSender messageQueueSender;
    private static final String ROUTING_KEY = "datagenerator.routingkey";

    public QueueMessageProcessor(MessageQueueSender messageQueueSender) {
        this.messageQueueSender = messageQueueSender;
    }

    @Override
    public void process(DataRecord dataRecord) {
        logger.info("Processing data for queue: {}", dataRecord);
        messageQueueSender.sendMessage(ROUTING_KEY, dataRecord);
    }
}
