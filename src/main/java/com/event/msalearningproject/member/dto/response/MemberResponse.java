package com.event.msalearningproject.member.dto.response;

import com.event.msalearningproject.member.entity.MessageType;
import com.event.msalearningproject.member.entity.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberResponse {

    private Long id;
    private String userId;
    private String password;
    private String phone;
    private MessageType messageType;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;

    public static MemberResponse from(MemberEntity member) {
        return MemberResponse
                .builder()
                .id(member.getId())
                .userId(member.getUserId())
                .password(member.getPassword())
                .phone(member.getPhone())
                .messageType(member.getMessageType())
                .createdTime(member.getCreatedTime())
                .updatedTime(member.getUpdatedTime())
                .build();
    }
}
