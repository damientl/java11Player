package com.development.playerapp.domain.player;

import com.development.playerapp.application.CommunicationOrchestrator;
import com.development.playerapp.application.Logger;
import com.development.playerapp.application.MessagesMediator;
import com.development.playerapp.utils.ObjectUtils;

public class PlayerValidator {
    private static final String DEFAULT_MESSAGE = "All player constructor arguments are required";

    public static void validate(
            String name,
            PlayerConsumer playerConsumer,
            MessagesMediator messagesMediator,
            CommunicationOrchestrator communicationOrchestrator,
            String partnerPlayer,
            Logger logger) {
        if (ObjectUtils.hasAnyNull(name,
                playerConsumer,
                messagesMediator,
                communicationOrchestrator,
                partnerPlayer,
                logger)) {
            throw new IllegalArgumentException(DEFAULT_MESSAGE);
        }
    }
}
