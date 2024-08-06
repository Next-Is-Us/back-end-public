package com.nextisus.project.exception.infopost;

import static com.nextisus.project.util.constant.StaticValue.INTERNAL_SERVER_ERROR;
import static com.nextisus.project.util.constant.StaticValue.NOT_FOUND;

import com.nextisus.project.util.exception.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum InfoPostErrorCode implements BaseErrorCode {

    INFO_POST_NOT_FOUND(NOT_FOUND,"INFO_POST_404","게시글이 존재하지 않습니다."),
    INFO_POST_INTERNAL_SERVER_ERROR(INTERNAL_SERVER_ERROR,"INFO_POST_500","데이터 베이스 오류가 발생했습니다.");

    private final int httpStatus;
    private final String code;
    private final String message;
}
