package com.nextisus.project.exception.condition;

import com.nextisus.project.util.exception.BaseException;

public class ConditionNotFoundException extends BaseException {
    public ConditionNotFoundException() {
        super(ConditionErrorCode.CONDITION_NOT_FOUND_1);
    }
}
