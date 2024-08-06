package com.nextisus.project.mom.room.dto;

import com.nextisus.project.domain.Room;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GetRoomDetailResponseDto {
    private Long roomId;
    private String name;
    private Long peopleCount;
    private Long necessaryNftCount;
    private String introduction;
    private String thumbnail;

    public static GetRoomDetailResponseDto from(Room room) {
        return GetRoomDetailResponseDto.builder()
                .roomId(room.getId())
                .name(room.getName())
                .peopleCount(room.getPeopleCount())
                .necessaryNftCount(room.getNecessaryNftCount())
                .introduction(room.getIntroduction())
                .thumbnail(room.getThumbnail())
                .build();
    }
}
