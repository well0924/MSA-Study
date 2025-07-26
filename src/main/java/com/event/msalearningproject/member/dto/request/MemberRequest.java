package com.event.msalearningproject.member.dto.request;

import com.event.msalearningproject.member.entity.MessageType;
import com.event.msalearningproject.member.entity.MemberEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MemberRequest {

    @NotBlank(message = "회원 아이디는 필수입니다.")
    private String userId;
    @NotBlank(message = "회원 비밀번호는 필수입니다.")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()\\-_=+{};:,<.>]).{8,}$",
            message = "비밀번호는 8자 이상이며, 영문 대소문자, 숫자, 특수문자를 포함해야 합니다."
    )
    private String password;
    @NotBlank(message = "전화번호는 필수입니다.")
    @Pattern(regexp = "^01[016789]-\\d{3,4}-\\d{4}$", message = "전화번호 형식이 올바르지 않습니다.")
    private String phone;
    @NotNull(message = "메시지 타입은 필수입니다.")
    private MessageType messageType;

    public MemberRequest to(MemberEntity member) {
        return MemberRequest
                .builder()
                .userId(member.getUserId())
                .password(member.getPassword())
                .messageType(member.getMessageType())
                .phone(member.getPhone())
                .build();
    }
}
