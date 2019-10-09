package com.development.playerapp.domain.model;

import com.development.playerapp.application.CommunicationOrchestrator;
import com.development.playerapp.domain.player.Player;

import java.util.Set;

public class PlayerSet {

    private final Set<Player> players;
    private final CommunicationOrchestrator communicationOrchestrator;

    public PlayerSet(Set<Player> players, CommunicationOrchestrator communicationOrchestrator) {
        this.players = players;
        this.communicationOrchestrator = communicationOrchestrator;
    }

    public Set<Player> getPlayers() {
        return players;
    }

    public CommunicationOrchestrator getCommunicationOrchestrator() {
        return communicationOrchestrator;
    }

}
