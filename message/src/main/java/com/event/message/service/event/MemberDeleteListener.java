package com.event.message.service.event;

import com.event.member.event.MemberDeleteEvent;
import com.event.message.service.MessageHistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberDeleteListener {

    private final MessageHistoryService messageHistoryService;

    @EventListener
    public void handleMemberDeleteEvent(MemberDeleteEvent event) {
        log.info("[EventListener] MemberDeleteEvent 수신. 메시지 이력 삭제 memberId: {}", event.getId());
        messageHistoryService.deleteByMemberId(event.getId());
    }
}
