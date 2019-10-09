package com.development.playerapp.domain.model;

public class PlayerPair {

    private final String player;
    private final String buddy;

    public PlayerPair(String player, String buddy) {
        this.player = player;
        this.buddy = buddy;
    }

    public String getPlayer() {
        return player;
    }

    public String getBuddy() {
        return buddy;
    }
}
