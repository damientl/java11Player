package com.development.playerapp.application;

/**
 * Classes implementing this Interface should be able to orchestrate conversation between player.
 * This includes starting and finishing conversation gracefully as soon as conversation can not continue
 */
public interface CommunicationOrchestrator {
    void waitConversationStarted();

    Boolean canCommunicationContinue(Integer receivedMessages, Integer sentMessages, String targetPlayer);

}
