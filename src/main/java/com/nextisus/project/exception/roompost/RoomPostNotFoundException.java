package com.nextisus.project.exception.roompost;

import com.nextisus.project.util.exception.BaseException;

public class RoomPostNotFoundException extends BaseException {
    public RoomPostNotFoundException() {
        super(RoomPostErrorCode.ROOM_POST_NOT_FOUND_1);
    }
}
