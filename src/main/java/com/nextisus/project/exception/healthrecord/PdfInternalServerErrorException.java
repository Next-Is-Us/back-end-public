package com.nextisus.project.exception.healthrecord;

import com.nextisus.project.util.exception.BaseException;

public class PdfInternalServerErrorException extends BaseException {
    public PdfInternalServerErrorException() {
        super(HealthRecordErrorCode.PDF_BAD_REQUEST);

    }

}
