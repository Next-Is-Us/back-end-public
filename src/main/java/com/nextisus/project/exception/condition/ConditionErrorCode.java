package com.nextisus.project.exception.condition;

import com.nextisus.project.util.exception.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.nextisus.project.util.constant.StaticValue.NOT_FOUND;

@Getter
@AllArgsConstructor
public enum ConditionErrorCode implements BaseErrorCode {

    CONDITION_NOT_FOUND_1(NOT_FOUND,"CONDITION_404_1","해당 날짜에 기록한 상태가 존재하지 않습니다"),
    CONDITION_NOT_FOUND_2(NOT_FOUND,"CONDITION_404_2","해당 유저가 기록한 상태가 존재하지 않습니다"),
    CONDITION_NOT_FOUND_3(NOT_FOUND,"CONDITION_404_3","해당 건강기록 ID로는 아직 의료기록을 변환할 수 없습니다.");

    private final int httpStatus;
    private final String code;
    private final String message;
}
