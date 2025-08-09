package com.event.message.service;

import com.event.common.enums.MessageType;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MessageSenderFactory {

    private final Map<MessageType, MessageSender> strategyMap = new HashMap<>();

    public MessageSenderFactory(List<MessageSender> strategies) {
        for (MessageSender sender : strategies) {
            strategyMap.put(sender.getMessageType(), sender);
        }
    }

    public MessageSender getSender(MessageType type) {
        return strategyMap.get(type);
    }
}
