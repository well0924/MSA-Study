package com.event.msalearningproject.config.exception.global;

import com.event.msalearningproject.config.exception.dto.ErrorCode;

public class UserNotFoundException extends ApiException {

    public UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}
