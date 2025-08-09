package com.event.common.exception.global;

import com.event.common.exception.dto.ErrorCode;

public class UserNotFoundException extends ApiException {

    public UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}
