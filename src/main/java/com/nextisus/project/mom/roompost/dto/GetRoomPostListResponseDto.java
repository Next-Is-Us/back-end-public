package com.nextisus.project.mom.roompost.dto;

import static com.nextisus.project.util.format.CreateAtFormat.formatToRelativeTime;

import com.nextisus.project.domain.RoomPost;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

// TODO: [주연] 댓글 개수 추가 필요
@Getter
@Setter
@Builder
public class GetRoomPostListResponseDto {
    private Long roomPostId;
    private String title;
    private String content;
    private String whenCreated;
    private Long commentCount;

    private static GetRoomPostListResponseDto fromRoomPost(RoomPost roomPost, Long commentCount) {
        return GetRoomPostListResponseDto.builder()
                .roomPostId(roomPost.getId())
                .title(roomPost.getTitle())
                .content(roomPost.getContent())
                .whenCreated(formatToRelativeTime(roomPost.getCreateAt()))
                .commentCount(commentCount)
                .build();
    }

    public static GetRoomPostListResponseDto from(RoomPost roomPost, Long commentCount) {
        return fromRoomPost(roomPost,commentCount);
    }
}
