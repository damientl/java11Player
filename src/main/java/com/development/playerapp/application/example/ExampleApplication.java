package com.development.playerapp.application.example;


import com.development.playerapp.application.Logger;
import com.development.playerapp.application.impl.ConsoleLoggerImpl;
import com.development.playerapp.domain.factory.PlayerSetFactory;
import com.development.playerapp.domain.model.PlayerPair;
import com.development.playerapp.domain.model.PlayerSet;
import com.development.playerapp.domain.player.Player;

import java.util.Arrays;

import static com.development.playerapp.domain.model.Players.INITIATOR;
import static com.development.playerapp.domain.model.Players.PLAYER_2;

/*
Class responsible for coordinating domain objects and starting application
 */
public class ExampleApplication {

    private final Logger logger = new ConsoleLoggerImpl();

    public synchronized void run() {
        PlayerSet playerSet = new PlayerSetFactory(this).createSet(
            Arrays.asList(
                new PlayerPair(INITIATOR, PLAYER_2),
                new PlayerPair(PLAYER_2, INITIATOR))
        );

        playerSet.getPlayers().forEach(Player::init);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.interrupted();
        }
        logger.debug("initialized players");
    }


}
