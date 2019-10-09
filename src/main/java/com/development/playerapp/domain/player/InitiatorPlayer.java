package com.development.playerapp.domain.player;

import com.development.playerapp.application.CommunicationOrchestrator;
import com.development.playerapp.application.Logger;
import com.development.playerapp.application.MessagesMediator;

public class InitiatorPlayer extends Player {


    public InitiatorPlayer(String name, PlayerConsumer playerConsumer, MessagesMediator messagesMediator, CommunicationOrchestrator communicationOrchestrator, String partnerPlayer, Logger logger) {
        super(name, playerConsumer, messagesMediator, communicationOrchestrator, partnerPlayer, logger);
    }

    @Override
    public void doAfterCommunicationStart() {
        speak("Hello Partner");
    }
}
