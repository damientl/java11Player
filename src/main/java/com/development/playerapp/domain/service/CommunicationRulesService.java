package com.development.playerapp.domain.service;

import static com.development.playerapp.domain.model.Players.PLAYER_2;

public class CommunicationRulesService {

    public Boolean shouldStopConversation(Integer receivedMessages, Integer sentMessages, String targetPlayer) {
        return receivedMessages >= 10 && sentMessages >= 10 && PLAYER_2.equals(targetPlayer);
    }
}
