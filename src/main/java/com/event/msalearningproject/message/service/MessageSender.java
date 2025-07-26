package com.event.msalearningproject.message.service;

import com.event.msalearningproject.member.entity.MessageType;

public interface MessageSender {

    void send(Long memberId,String contents,String userId);

    MessageType getMessageType();
}
