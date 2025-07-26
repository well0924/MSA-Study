package com.event.msalearningproject.config.exception.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    // 4xx - 클라이언트 에러
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "U001", "회원이 존재하지 않습니다."),
    INVALID_REQUEST(HttpStatus.BAD_REQUEST, "C001", "잘못된 요청입니다."),

    // 5xx - 서버 에러
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "S001", "서버 오류가 발생했습니다."),
    DATABASE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "S002", "데이터베이스 오류입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
