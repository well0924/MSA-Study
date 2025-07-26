package com.event.msalearningproject.message.dto.request;

import com.event.msalearningproject.member.entity.MessageType;
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
