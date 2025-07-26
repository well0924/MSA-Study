package com.event.msalearningproject.message.dto.response;

import com.event.msalearningproject.member.entity.MessageType;
import com.event.msalearningproject.message.entity.MessageHistoryEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageResponse {

    private Long id;

    private String contents;

    private MessageType messageType;

    private boolean isSent;

    private String failReason;

    private Long memberId;

    private String userId;

    private LocalDateTime createdTime;

    private LocalDateTime updatedTime;

    public static MessageResponse from(MessageHistoryEntity messageHistoryEntity) {
        return MessageResponse
                .builder()
                .id(messageHistoryEntity.getId())
                .contents(messageHistoryEntity.getContents())
                .messageType(messageHistoryEntity.getMessageType())
                .isSent(messageHistoryEntity.isSent())
                .failReason(messageHistoryEntity.getFailReason())
                .memberId(messageHistoryEntity.getMemberId())
                .userId(messageHistoryEntity.getUserId())
                .createdTime(messageHistoryEntity.getCreatedTime())
                .updatedTime(messageHistoryEntity.getUpdatedTime())
                .build();
    }
}
