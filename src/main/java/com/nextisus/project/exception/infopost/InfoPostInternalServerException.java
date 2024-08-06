package com.nextisus.project.exception.infopost;

import com.nextisus.project.util.exception.BaseException;

public class InfoPostInternalServerException extends BaseException {
    public InfoPostInternalServerException() {
        super(InfoPostErrorCode.INFO_POST_INTERNAL_SERVER_ERROR);
    }
}
