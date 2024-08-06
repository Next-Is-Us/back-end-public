package com.nextisus.project.exception.room;

import com.nextisus.project.util.exception.BaseException;

public class RoomNameDuplicatedException extends BaseException {
    public RoomNameDuplicatedException() {
        super(RoomErrorCode.ROOM_NAME_DUPLICATED);
    }
}
