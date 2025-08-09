package com.event.message.service;


import com.event.common.enums.MessageType;

public interface MessageSender {

    void send(Long memberId,String contents,String userId);

    MessageType getMessageType();
}
