//package com.bundle.socketlistener.handler;
//
//import com.bundle.socketlistener.common.MessageHandlingException;
//import com.bundle.socketlistener.common.QueueException;
//import com.bundle.socketlistener.messaging.MessageQueueSender;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//public class QueueMessageHandler implements IMessageHandler {
//    private static final Logger logger = LoggerFactory.getLogger(QueueMessageHandler.class);
//
//    private final MessageQueueSender messageQueue;
//
//    public QueueMessageHandler(MessageQueueSender messageQueue) {
//        this.messageQueue = messageQueue;
//    }
//
//    @Override
//    public void handle(String message) throws MessageHandlingException {
//        try {
//            messageQueue.send(message);
//            logger.debug("Message sent to queue: {}", message);
//        } catch (QueueException e) {
//            logger.error("Failed to send message to queue", e);
//            throw new MessageHandlingException("Failed to send message to queue", e);
//        }
//    }
//}
