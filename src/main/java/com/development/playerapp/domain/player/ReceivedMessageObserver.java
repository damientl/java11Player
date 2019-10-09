package com.development.playerapp.domain.player;

/**
 * Classes implementing this Interface are able to observe received messages from an object
 */
public interface ReceivedMessageObserver {
    void receivedMessage(String message);
}
