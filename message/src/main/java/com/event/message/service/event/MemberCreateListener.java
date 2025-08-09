package com.event.message.service.event;


import com.event.member.event.MemberCreateEvent;
import com.event.message.service.MessageHistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberCreateListener {

    private final MessageHistoryService messageHistoryService;

    @EventListener
    public void handleMemberCreatedEvent(MemberCreateEvent event) {
        log.info("[EventListener] MemberCreatedEvent 수신. userId: {}", event.getUserId());
        // 이벤트 데이터를 사용하여 MessageHistoryService의 send 메서드 호출
        messageHistoryService.send(
                event.getMemberId(),
                event.getUserId(),
                event.getMessageContent(),
                event.getMessageType()
        );
    }
}
