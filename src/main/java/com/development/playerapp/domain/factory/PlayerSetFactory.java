package com.development.playerapp.domain.factory;

import com.development.playerapp.application.CommunicationOrchestrator;
import com.development.playerapp.application.Logger;
import com.development.playerapp.application.MessagesMediator;
import com.development.playerapp.application.impl.CommunicationOrchestratorImpl;
import com.development.playerapp.application.impl.ConsoleLoggerImpl;
import com.development.playerapp.application.impl.MessagesMediatorImpl;
import com.development.playerapp.domain.model.PlayerPair;
import com.development.playerapp.domain.model.PlayerSet;
import com.development.playerapp.domain.player.InitiatorPlayer;
import com.development.playerapp.domain.player.Player;
import com.development.playerapp.domain.player.PlayerConsumer;
import com.development.playerapp.infrastructure.MessageBroker;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;

import static com.development.playerapp.domain.model.Players.INITIATOR;

/**
 * Factory to create player objects
 */
public class PlayerSetFactory {

    private final Integer BOUND = 10;

    private final MessageBroker messageBroker;
    private final MessagesMediator mediator;
    private final CommunicationOrchestrator communicationOrchestrator;
    private final Logger logger = new ConsoleLoggerImpl();

    public PlayerSetFactory() {
        messageBroker = new MessageBroker();
        mediator = new MessagesMediatorImpl(messageBroker, logger);
        communicationOrchestrator = new CommunicationOrchestratorImpl(messageBroker);
    }

    public PlayerSet createSet(List<PlayerPair> playerPairs) {

        return new PlayerSet(
                playerPairs.stream().map(this::createPlayer).collect(Collectors.toSet())
                , communicationOrchestrator
        );
    }

    public Player createPlayer(PlayerPair pair) {
        if (INITIATOR.equals(pair.getPlayer())) {
            return new InitiatorPlayer(pair.getPlayer(),
                    createPlayerConsumer(pair),
                    mediator,
                    communicationOrchestrator,
                    pair.getPartnerPlayer(),
                    logger
            );
        }
        return new Player(pair.getPlayer(),
                createPlayerConsumer(pair),
                mediator,
                communicationOrchestrator,
                pair.getPartnerPlayer(),
                logger
        );
    }

    private PlayerConsumer createPlayerConsumer(PlayerPair pair) {
        messageBroker.createPlayerQueue(pair.getPlayer(), new LinkedBlockingQueue<>(BOUND));
        return new PlayerConsumer(
                pair.getPlayer(),
                messageBroker.getPlayerQueue(pair.getPlayer()),
                logger);
    }
}
