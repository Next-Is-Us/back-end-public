package com.nextisus.project.exception.room;

import com.nextisus.project.util.exception.BaseException;

public class RoomNotFoundException extends BaseException {
    public RoomNotFoundException() {
        super(RoomErrorCode.ROOM_NOT_FOUND);
    }
}
