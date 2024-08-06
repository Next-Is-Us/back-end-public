package com.nextisus.project.doctor.roompost.dto;

import com.nextisus.project.domain.RoomPost;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreateRoomPostResponseDto {
    private Long roomPostId;

    public static CreateRoomPostResponseDto of(RoomPost roomPost) {
        return CreateRoomPostResponseDto.builder()
                .roomPostId(roomPost.getId())
                .build();
    }
}
