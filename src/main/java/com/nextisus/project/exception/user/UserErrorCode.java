package com.nextisus.project.exception.user;

import static com.nextisus.project.util.constant.StaticValue.DUPLICATED;
import static com.nextisus.project.util.constant.StaticValue.NOT_FOUND;

import com.nextisus.project.util.exception.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserErrorCode implements BaseErrorCode {

    USER_NOT_FOUND(NOT_FOUND, "USER_404_1", "사용자가 존재하지 않습니다."),
    USER_DUPLICATED(DUPLICATED, "USER_409_1", "가족 내에서 같은 닉네임을 사용할 수 없습니다.");

    private final int httpStatus;
    private final String code;
    private final String message;
}