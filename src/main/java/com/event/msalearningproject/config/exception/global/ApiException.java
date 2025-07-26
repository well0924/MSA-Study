package com.event.msalearningproject.config.exception.global;

import com.event.msalearningproject.config.exception.dto.ErrorCode;
import lombok.Getter;

@Getter
public class ApiException extends RuntimeException{

    private final ErrorCode errorCode;

    public ApiException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
