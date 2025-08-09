package com.event.message.dto.request;

import com.event.common.enums.MessageType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageRequest {
    private Long memberId;
    private String userId;
    private String contents;
    private MessageType type;
}
