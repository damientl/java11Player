package com.development.playerapp.application.impl;

import com.development.playerapp.application.CommunicationOrchestrator;
import com.development.playerapp.domain.model.Message;
import com.development.playerapp.domain.model.MessageType;
import com.development.playerapp.domain.service.CommunicationRulesService;
import com.development.playerapp.infrastructure.MessageBroker;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Communication Orchestrator following @see CommunicationRulesService rules
 */
public class CommunicationOrchestratorImpl implements CommunicationOrchestrator {
    private final CommunicationRulesService communicationRulesService = new CommunicationRulesService();
    private final MessageBroker messageBroker;

    private Object communicationChannel;

    private void killPlayers() {
        messageBroker.broadcast(new Message("", MessageType.POISON_PILL));
    }

    public CommunicationOrchestratorImpl(MessageBroker messageBroker,
        Object communicationChannel) {
        this.messageBroker = messageBroker;
        this.communicationChannel = communicationChannel;
    }


    public void waitConversationStarted() {
      synchronized (communicationChannel) {}
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
