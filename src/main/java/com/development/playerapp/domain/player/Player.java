package com.development.playerapp.domain.player;

import com.development.playerapp.application.CommunicationOrchestrator;
import com.development.playerapp.application.Logger;
import com.development.playerapp.application.MessagesMediator;

import java.util.Objects;

import static com.development.playerapp.domain.model.Players.INITIATOR;

/**
 * Class representing Player domain object and defines its behavior
 * <p>
 * Player runs in a separate thread and listens to messages from other players
 * Player continues conversation if allowed by @see CommunicationOrchestrator
 */
public class Player implements ReceivedMessageObserver, Runnable {
    private final PlayerConsumer playerConsumer;
    private final MessagesMediator messagesMediator;
    private final CommunicationOrchestrator communicationOrchestrator;
    private final String name;
    private final String buddyPlayer;
    private final Logger logger;
    private Integer sentMessages = 0;
    private Integer receivedMessages = 0;

    public Player(
            String name,
            PlayerConsumer playerConsumer,
            MessagesMediator messagesMediator,
            CommunicationOrchestrator communicationOrchestrator,
            String buddyPlayer,
            Logger logger
    ) {
        playerConsumer.setReceivedMessageObserver(this);
        this.playerConsumer = playerConsumer;
        this.messagesMediator = messagesMediator;
        this.communicationOrchestrator = communicationOrchestrator;
        this.name = name;
        this.buddyPlayer = buddyPlayer;
        this.logger = logger;
    }

    public void init() {
        new Thread(this).start();
    }

    @Override
    public void run() {

        if (INITIATOR.equals(name)) {
            communicationOrchestrator.waitConversationStarted();
            speak("Hello Buddy");
        }

        playerConsumer.listenToQueue();
    }

    private void speak(String text) {
        logger.info(
                "------------------------\n" +
                        name + " - Sending message: " + text + "\n" +
                        "------------------------\n");
        messagesMediator.sendMessage(text, buddyPlayer);
        sentMessages++;
    }

    public String getName() {
        return name;
    }

    @Override
    public void receivedMessage(String message) {
        receivedMessages++;
        if (communicationOrchestrator.canCommunicationContinue(receivedMessages, sentMessages, buddyPlayer)) {
            speak(message + ", count: " + sentMessages);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Player that = (Player) o;
        return Objects.equals(name, that.name);
    }

}
