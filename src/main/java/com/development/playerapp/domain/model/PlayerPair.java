package com.development.playerapp.domain.model;

public class PlayerPair {

    private final String player;
    private final String partnerPlayer;

    public PlayerPair(String player, String partnerPlayer) {
        this.player = player;
        this.partnerPlayer = partnerPlayer;
    }

    public String getPlayer() {
        return player;
    }

    public String getPartnerPlayer() {
        return partnerPlayer;
    }
}
