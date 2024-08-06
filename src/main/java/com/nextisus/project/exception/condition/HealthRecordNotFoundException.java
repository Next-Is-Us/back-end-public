package com.nextisus.project.exception.condition;

import com.nextisus.project.util.exception.BaseException;

public class HealthRecordNotFoundException extends BaseException {
    public HealthRecordNotFoundException() {
        super(ConditionErrorCode.CONDITION_NOT_FOUND_3);
    }
}
