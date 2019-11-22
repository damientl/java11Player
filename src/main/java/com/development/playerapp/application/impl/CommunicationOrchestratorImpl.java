package com.development.playerapp.application.impl;

import com.development.playerapp.application.CommunicationOrchestrator;
import com.development.playerapp.domain.model.Message;
import com.development.playerapp.domain.model.MessageType;
import com.development.playerapp.domain.service.CommunicationRulesService;
import com.development.playerapp.infrastructure.MessageBroker;

/**
 * Communication Orchestrator following @see CommunicationRulesService rules
 */
public class CommunicationOrchestratorImpl implements CommunicationOrchestrator {
    private final CommunicationRulesService communicationRulesService = new CommunicationRulesService();
    private final MessageBroker messageBroker;

    private Object communicationMonitor = new Object();
    boolean conversationStarted = false;

    private void killPlayers() {
        messageBroker.broadcast(new Message("", MessageType.POISON_PILL));
    }

    public CommunicationOrchestratorImpl(MessageBroker messageBroker) {
        this.messageBroker = messageBroker;
    }


    public void waitConversationStarted() {
      synchronized(communicationMonitor){
          while(!conversationStarted){
              try{
                  communicationMonitor.wait();
              } catch(InterruptedException e){
                  Thread.interrupted();
              }
          }
      }
    }
    public void startConversation() {
        synchronized(communicationMonitor){
            conversationStarted = true;
            communicationMonitor.notifyAll();
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
