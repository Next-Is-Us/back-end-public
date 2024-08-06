package com.nextisus.project.mom.roompost.dto;

import com.nextisus.project.domain.RoomPost;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GetRoomPostDetailResponseDto {
    private Long roomPostId;
    private String title;
    private String content;
    private Integer viewCount;

    private static GetRoomPostDetailResponseDto fromRoomPost(RoomPost roomPost) {
        return GetRoomPostDetailResponseDto.builder()
                .roomPostId(roomPost.getId())
                .title(roomPost.getTitle())
                .content(roomPost.getContent())
                .viewCount(roomPost.getViewCount())
                .build();
    }

    public static GetRoomPostDetailResponseDto from(RoomPost roomPost) {
        return fromRoomPost(roomPost);
    }
}
