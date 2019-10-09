package com.development.playerapp.application.impl;

import com.development.playerapp.application.Logger;
import com.development.playerapp.application.MessagesMediator;
import com.development.playerapp.domain.model.Message;
import com.development.playerapp.domain.model.MessageType;
import com.development.playerapp.infrastructure.MessageBroker;

/**
 * Mediator interface implementation
 */
public class MessagesMediatorImpl implements MessagesMediator {

    private final MessageBroker messageBroker;
    private final Logger logger;

    public MessagesMediatorImpl(MessageBroker messageBroker, Logger logger) {
        this.messageBroker = messageBroker;
        this.logger = logger;
    }

    public void sendMessage(String messageText, String targetPlayer) {
        try {
            Message message = new Message(messageText, MessageType.MESSAGE);

            logger.debug("Mediator - " + Thread.currentThread().getName() + " - To: " + targetPlayer +
                    " \n Sent message: " + message.getMessage() +
                    " \n");

            messageBroker.sendMessageToPlayer(
                    targetPlayer,
                    message);


        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
