package com.bundle.socketlistener.listener;

import com.bundle.socketlistener.model.DataRecord;
import com.bundle.socketlistener.processor.IMessageProcessor;
import com.bundle.socketlistener.processor.MessageProcessorSelector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SocketListener {
    private static final Logger logger = LoggerFactory.getLogger(SocketListener.class);
    private final MessageProcessorSelector processorSelector;

    public SocketListener(MessageProcessorSelector processorSelector) {
        this.processorSelector = processorSelector;
    }

    public void onMessageReceived(DataRecord dataRecord) {
        logger.info("Received data: {}", dataRecord);
        IMessageProcessor processor = processorSelector.selectProcessor(dataRecord);
        processor.process(dataRecord);
    }
}
