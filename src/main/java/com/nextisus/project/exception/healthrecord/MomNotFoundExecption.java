package com.nextisus.project.exception.healthrecord;

import com.nextisus.project.util.exception.BaseException;

public class MomNotFoundExecption extends BaseException{
    public MomNotFoundExecption() {
        super(HealthRecordErrorCode.MOM_NOT_FOUND);
    }
}
