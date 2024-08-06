package com.nextisus.project.util.exception;

public interface BaseErrorCode {

    String getCode();
    String getMessage();
    int getHttpStatus();
}
