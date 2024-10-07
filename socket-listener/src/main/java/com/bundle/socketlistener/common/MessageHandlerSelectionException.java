package com.bundle.socketlistener.common;

public class MessageHandlerSelectionException extends Exception {
    public MessageHandlerSelectionException(String message) {
        super(message);
    }

    public MessageHandlerSelectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
