package com.nextisus.project.exception.roompost;

import com.nextisus.project.util.exception.BaseException;

public class RoomPostCreateForbiddenException extends BaseException {
    public RoomPostCreateForbiddenException() {
        super(RoomPostErrorCode.ROOM_POST_FORBIDDEN_1);
    }
}
