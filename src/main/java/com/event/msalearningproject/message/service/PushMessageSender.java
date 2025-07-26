package com.event.msalearningproject.message.service;

import com.event.msalearningproject.member.entity.MessageType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PushMessageSender implements MessageSender{

    @Override
    public void send(Long memberId, String contents, String userId) {
        log.info("[푸시 전송] {}번 회원에게 알림 푸시 발송: {} ",memberId,contents);
    }

    @Override
    public MessageType getMessageType() {
        return MessageType.PUSH;
    }
}
