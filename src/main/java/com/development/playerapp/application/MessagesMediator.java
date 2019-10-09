package com.development.playerapp.application;

/**
 * Classes implementing this Interface should be able to mediate messages sent to a player to another
 * and start conversation
 */
public interface MessagesMediator {

    void sendMessage(String message, String player);
}
