package com.nextisus.project.mom.roomcomment.service;

import com.nextisus.project.mom.roomcomment.dto.CreateCommentRequestDto;
import com.nextisus.project.mom.roomcomment.dto.RoomCommentListDto;
import com.nextisus.project.util.response.PageResponse;
import com.nextisus.project.util.response.SuccessResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MomRoomCommentService {

    SuccessResponse<?> createComment(CreateCommentRequestDto dto, Long userId);

    PageResponse<RoomCommentListDto> getComments(Long roomPostId, Pageable pageable);
}
