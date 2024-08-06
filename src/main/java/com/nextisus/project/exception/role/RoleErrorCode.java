package com.nextisus.project.exception.role;

import static com.nextisus.project.util.constant.StaticValue.NOT_FOUND;

import com.nextisus.project.util.exception.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleErrorCode implements BaseErrorCode {

    ROLE_NOT_FOUND(NOT_FOUND, "ROLE_404_1", "역할이 존재하지 않습니다.");

    private final int httpStatus;
    private final String code;
    private final String message;
}