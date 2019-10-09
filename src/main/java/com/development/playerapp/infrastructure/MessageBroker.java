package com.development.playerapp.infrastructure;

import com.development.playerapp.domain.model.Message;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

/**
 * Message Broker for player queues
 */
public class MessageBroker {

    private final Map<String, BlockingQueue<Message>> playerQueues = new HashMap<>();

    public void createPlayerQueue(String name, BlockingQueue<Message> queue) {
        playerQueues.put(name, queue);
    }

    public void sendMessageToPlayer(String player, Message message) throws InterruptedException {
        playerQueues.get(player).put(message);
    }

    public void broadcast(Message message) {
        playerQueues.forEach((key, value) -> {
            try {
                sendMessageToPlayer(key, message);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
    }

    public BlockingQueue<Message> getPlayerQueue(String player) {
        return playerQueues.get(player);
    }
}
