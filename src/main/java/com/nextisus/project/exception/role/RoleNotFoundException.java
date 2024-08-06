package com.nextisus.project.exception.role;

import com.nextisus.project.util.exception.BaseException;

public class RoleNotFoundException extends BaseException {
    public RoleNotFoundException() {
        super(RoleErrorCode.ROLE_NOT_FOUND);
    }
}
