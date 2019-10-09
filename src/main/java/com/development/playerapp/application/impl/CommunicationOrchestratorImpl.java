package com.development.playerapp.application.impl;

import com.development.playerapp.application.CommunicationOrchestrator;
import com.development.playerapp.domain.model.Message;
import com.development.playerapp.domain.model.MessageType;
import com.development.playerapp.domain.service.CommunicationRulesService;
import com.development.playerapp.infrastructure.MessageBroker;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Communication Orchestrator following @see CommunicationRulesService rules
 */
public class CommunicationOrchestratorImpl implements CommunicationOrchestrator {
    private static final int WAIT_LOOP_TIME = 100;
    private final Lock lock = new ReentrantLock();
    private Boolean conversationStared = false;
    private final CommunicationRulesService communicationRulesService = new CommunicationRulesService();
    private final MessageBroker messageBroker;

    private void killPlayers() {
        messageBroker.broadcast(new Message("", MessageType.POISON_PILL));
    }

    public CommunicationOrchestratorImpl(MessageBroker messageBroker) {
        this.messageBroker = messageBroker;
        lock.lock();
    }

    @Override
    public void startConversation() {
        lock.unlock();
    }

    public void waitConversationStarted() {
        try {
            while (!conversationStared) {
                conversationStared = lock.tryLock(WAIT_LOOP_TIME, TimeUnit.SECONDS);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public Boolean canCommunicationContinue(Integer receivedMessages, Integer sentMessages, String targetPlayer) {
        if (communicationRulesService.shouldStopConversation(receivedMessages, sentMessages, targetPlayer)) {
            killPlayers();
            return false;
        }
        return true;
    }
}
