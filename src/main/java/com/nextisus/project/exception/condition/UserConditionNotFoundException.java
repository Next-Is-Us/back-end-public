package com.nextisus.project.exception.condition;

import com.nextisus.project.util.exception.BaseException;

public class UserConditionNotFoundException extends BaseException {
    public UserConditionNotFoundException() {
        super(ConditionErrorCode.CONDITION_NOT_FOUND_2);
    }
}
