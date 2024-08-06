package com.nextisus.project.exception.room;

import static com.nextisus.project.util.constant.StaticValue.BAD_REQUEST;
import static com.nextisus.project.util.constant.StaticValue.DUPLICATED;
import static com.nextisus.project.util.constant.StaticValue.NOT_FOUND;

import com.nextisus.project.util.exception.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoomErrorCode implements BaseErrorCode {
    ROOM_NFT_NOT_ENOUGH(BAD_REQUEST, "ROOM_400_1", "NFT 개수가 부족합니다."),
    ROOM_NOT_FOUND(NOT_FOUND,"ROOM_404_1","소통방을 찾을 수 없습니다."),
    ROOM_NAME_DUPLICATED(DUPLICATED, "ROOM_409_1", "이미 사용중인 소통방 이름입니다.");

    private final int httpStatus;
    private final String code;
    private final String message;
}
