package com.nextisus.project.exception.room;

import com.nextisus.project.util.exception.BaseException;

public class RoomNftNotEnough extends BaseException {
    public RoomNftNotEnough() {
        super(RoomErrorCode.ROOM_NFT_NOT_ENOUGH);
    }
}
