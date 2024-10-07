package com.bundle.socketlistener.processor;

import com.bundle.socketlistener.appender.FileAppender;
import com.bundle.socketlistener.model.DataRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class FileMessageProcessor implements IMessageProcessor {
    private static final Logger logger = LoggerFactory.getLogger(FileMessageProcessor.class);
    private final FileAppender fileAppender;

    public FileMessageProcessor(FileAppender fileAppender) {
        this.fileAppender = fileAppender;
    }

    @Override
    public void process(DataRecord dataRecord) {
        logger.info("Processing data for file: {}", dataRecord);
        fileAppender.appendToFile(dataRecord);
    }
}
