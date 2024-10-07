package com.bundle.socketlistener.processor;

import com.bundle.socketlistener.model.DataRecord;
import org.springframework.stereotype.Component;

@Component
public class MessageProcessorSelector {
    private final QueueMessageProcessor queueMessageProcessor;
    private final FileMessageProcessor fileMessageProcessor;

    public MessageProcessorSelector(QueueMessageProcessor queueMessageProcessor,
                                    FileMessageProcessor fileMessageProcessor) {
        this.queueMessageProcessor = queueMessageProcessor;
        this.fileMessageProcessor = fileMessageProcessor;
    }

    public IMessageProcessor selectProcessor(DataRecord dataRecord) {
        if (dataRecord.getRandomNumber() > 10) {
            return queueMessageProcessor;
        } else {
            return fileMessageProcessor;
        }
    }
}
