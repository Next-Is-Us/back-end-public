package com.nextisus.project.exception.healthrecord;

import com.nextisus.project.util.exception.BaseException;

public class HealthRecordNotFoundException extends BaseException {
    public HealthRecordNotFoundException() {
        super(HealthRecordErrorCode.HEALTH_RECORD_NOT_FOUND);
    }
}
