package com.development.playerapp.domain.player;

import com.development.playerapp.application.Logger;
import com.development.playerapp.domain.model.Message;
import com.development.playerapp.domain.model.MessageType;

import java.util.concurrent.BlockingQueue;

/**
 * Class responsible for listening messages sent to a player
 */
public class PlayerConsumer {

    private final BlockingQueue<Message> receivedMessageQueue;
    private final Logger logger;
    private final String name;
    private ReceivedMessageObserver receivedMessageObserver;

    public PlayerConsumer(
            String name,
            BlockingQueue<Message> receivedMessageQueue,
            Logger logger
    ) {
        this.receivedMessageQueue = receivedMessageQueue;
        this.name = name;
        this.logger = logger;
    }

    public void setReceivedMessageObserver(ReceivedMessageObserver receivedMessageObserver) {
        this.receivedMessageObserver = receivedMessageObserver;
    }

    public void listenToQueue() {
        try {
            while (true) {
                Message messageItem = receivedMessageQueue.take();
                if (MessageType.POISON_PILL == messageItem.getType()) {
                    return;
                }
                logger.info(
                        "Consumer: " + Thread.currentThread().getName() + " - " + name +
                                " \n Received message: " + messageItem.getMessage() +
                                " \n");

                notifyObserver(messageItem.getMessage());
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void notifyObserver(String message) {
        if (receivedMessageObserver == null) {
            return;
        }
        receivedMessageObserver.receivedMessage(message);
    }


}
