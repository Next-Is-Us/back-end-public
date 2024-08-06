package com.nextisus.project.mom.room.dto;

import com.nextisus.project.domain.Room;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GetRoomListResponseDto {
    private Long roomId;
    private String name;
    private Long peopleCount;
    private Long necessaryNftCount;
    private Boolean isPossibleToEnter;
    private String thumbnail;

    private static GetRoomListResponseDto fromRoom(Room room, Long userNftCount) {
        return GetRoomListResponseDto.builder()
                .roomId(room.getId())
                .name(room.getName())
                .peopleCount(room.getPeopleCount())
                .necessaryNftCount(room.getNecessaryNftCount())
                .isPossibleToEnter(isPossibleToEnter(userNftCount, room.getNecessaryNftCount()))
                .thumbnail(room.getThumbnail())
                .build();
    }

    public static GetRoomListResponseDto from(Room room, Long userNftCount) {
        return fromRoom(room, userNftCount);
    }

    private static Boolean isPossibleToEnter(Long userNftCount, Long necessaryNftCount) {
        if (userNftCount < necessaryNftCount) return false;
        return true;
    }
}
