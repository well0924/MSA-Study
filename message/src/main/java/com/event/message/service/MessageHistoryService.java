package com.event.message.service;

import com.event.common.enums.MessageType;
import com.event.message.dto.request.MessageRequest;
import com.event.message.dto.response.MessageResponse;
import com.event.message.entity.MessageHistoryEntity;
import com.event.message.repository.MessageHistoryRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class MessageHistoryService {

    private final MessageHistoryRepository messageHistoryRepository;

    private final MessageSenderFactory senderFactory;

    public void send(Long memberId, String userId, String contents, MessageType type) {
        boolean isSent = false;
        String failReason = null;

        try {
            MessageSender sender = senderFactory.getSender(type);
            sender.send(memberId, userId, contents);
            isSent = true;
        } catch (Exception e) {
            log.error("[메시지 전송 실패] memberId={}, userId={}, type={}, error={}",
                    memberId, userId, type, e.getMessage(), e);
            failReason = e.getMessage();
        }

        messageHistoryRepository.save(MessageHistoryEntity.builder()
                .memberId(memberId)
                .contents(contents)
                .userId(userId)
                .messageType(type)
                .isSent(isSent)
                .failReason(failReason)
                .build()
        );
    }

    public void sendBulk(List<MessageRequest> requests) {
        for (MessageRequest req : requests) {
            send(req.getMemberId(), req.getUserId(), req.getContents(), req.getType());
        }
    }

    @Transactional(readOnly = true)
    public List<MessageResponse> findByMemberId(Long memberId) {
        return messageHistoryRepository.findByMemberId(memberId)
                .stream()
                .map(MessageResponse::from)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<MessageResponse> findByUserId(String userId) {
        return messageHistoryRepository.findByUserId(userId)
                .stream()
                .map(MessageResponse::from)
                .collect(Collectors.toList());
    }

    public void deleteByMemberId(Long memberId) {
        log.info("[메시지 이력 삭제] memberId={}", memberId);
        messageHistoryRepository.deleteByMemberId(memberId);
    }
}
