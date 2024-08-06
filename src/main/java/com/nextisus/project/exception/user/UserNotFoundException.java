package com.nextisus.project.exception.user;

import com.nextisus.project.util.exception.BaseException;

public class UserNotFoundException extends BaseException {
    public UserNotFoundException() {
        super(UserErrorCode.USER_NOT_FOUND);
    }
}
