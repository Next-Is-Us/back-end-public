package com.nextisus.project.exception.link;

import static com.nextisus.project.util.constant.StaticValue.NOT_FOUND;

import com.nextisus.project.util.exception.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LinkErrorCode implements BaseErrorCode {

    LINK_NOT_FOUND(NOT_FOUND, "LINK_404_1", "링크가 존재하지 않습니다.");

    private final int httpStatus;
    private final String code;
    private final String message;
}