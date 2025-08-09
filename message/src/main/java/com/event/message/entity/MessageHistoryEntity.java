package com.event.message.entity;

import com.event.common.enums.MessageType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "message_history")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String contents;

    @Enumerated(EnumType.STRING)
    private MessageType messageType;

    private boolean isSent;

    private String failReason;

    private Long memberId;

    private String userId;

    private LocalDateTime createdTime;

    private LocalDateTime updatedTime;
}
