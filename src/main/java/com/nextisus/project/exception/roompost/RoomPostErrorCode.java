package com.nextisus.project.exception.roompost;

import static com.nextisus.project.util.constant.StaticValue.FORBIDDEN;
import static com.nextisus.project.util.constant.StaticValue.NOT_FOUND;

import com.nextisus.project.util.exception.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoomPostErrorCode implements BaseErrorCode {

    ROOM_POST_FORBIDDEN_1(FORBIDDEN,"ROOM_POST_403_1","해당 방에 접근 권한이 없습니다."),
    ROOM_POST_NOT_FOUND_1(NOT_FOUND,"ROOM_POST_404_1","존재하지 않는 게시글입니다.");

    private final int httpStatus;
    private final String code;
    private final String message;
}
