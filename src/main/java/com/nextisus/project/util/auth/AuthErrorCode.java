package com.nextisus.project.util.auth;

import static com.nextisus.project.util.constant.StaticValue.UNAUTHORIZED;

import com.nextisus.project.util.exception.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AuthErrorCode implements BaseErrorCode {

    MISSING_AUTH_TOKEN(UNAUTHORIZED, "GLOBAL_401_1", "헤더에 토큰값을 작성해주세요."),
    INVALID_AUTH_TOKEN(UNAUTHORIZED, "GLOBAL_401_2", "헤더에 올바른 토큰값을 작성해주세요.");

    private final int httpStatus;
    private final String code;
    private final String message;
}