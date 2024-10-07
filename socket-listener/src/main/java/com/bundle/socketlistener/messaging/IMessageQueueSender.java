package com.bundle.socketlistener.messaging;

public interface IMessageQueueSender {
    void sendMessage(String routingKey, Object message);
}
