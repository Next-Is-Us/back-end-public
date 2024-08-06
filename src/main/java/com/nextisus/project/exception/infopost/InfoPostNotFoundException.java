package com.nextisus.project.exception.infopost;

import com.nextisus.project.util.exception.BaseException;

public class InfoPostNotFoundException extends BaseException {
    public InfoPostNotFoundException() {
        super(InfoPostErrorCode.INFO_POST_NOT_FOUND);
    }
}
