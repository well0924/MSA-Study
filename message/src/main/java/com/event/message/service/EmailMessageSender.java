package com.event.message.service;

import com.event.common.enums.MessageType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmailMessageSender implements MessageSender {

    @Override
    public void send(Long memberId, String contents, String userId) {
        log.info("[이메일 전송] {}번 회원에게 이메일 메시지를 발송: {} ",memberId,contents);
    }

    @Override
    public MessageType getMessageType() {
        return MessageType.MAIL;
    }
}
