package com.event.message.service;

import com.event.common.enums.MessageType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KakaoMessageSender implements MessageSender {

    @Override
    public void send(Long memberId, String contents, String userId) {
        log.info("[KAKAO]{}번 회원에게 메시지 발송: {}" , memberId ,contents);
    }

    @Override
    public MessageType getMessageType() {
        return MessageType.KAKAO_TALK;
    }
}
