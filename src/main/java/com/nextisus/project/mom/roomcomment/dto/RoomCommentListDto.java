package com.nextisus.project.mom.roomcomment.dto;

import com.nextisus.project.domain.RoomComment;
import lombok.*;

@Getter
@Setter
@Builder
public class RoomCommentListDto {
    private Long commentId;
    private String commentContent;
    private String author;

    public static RoomCommentListDto fromRoomComment(RoomComment roomComment) {
        return RoomCommentListDto.builder()
                .commentId(roomComment.getCommentId())
                .commentContent(roomComment.getCommentContent())
                .author(roomComment.getUser().getNickname())
                .build();
    }
    public static RoomCommentListDto from(RoomComment roomComment) {
        return fromRoomComment(roomComment);
    }

}
