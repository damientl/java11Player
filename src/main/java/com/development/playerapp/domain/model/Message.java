package com.development.playerapp.domain.model;

public class Message {

    private final String message;
    private final MessageType type;

    public Message(String message, MessageType type) {
        this.message = message;
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public MessageType getType() {
        return type;
    }

}
