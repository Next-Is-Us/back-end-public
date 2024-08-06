package com.nextisus.project.mom.roompost.service;

import com.nextisus.project.mom.roompost.dto.GetRoomPostDetailResponseDto;
import com.nextisus.project.mom.roompost.dto.GetRoomPostListResponseDto;
import com.nextisus.project.util.response.PageResponse;
import org.springframework.data.domain.Pageable;

public interface MomRoomPostService {
    PageResponse<GetRoomPostListResponseDto> getRoomPostList(Long roomId, Pageable pageable);
    GetRoomPostDetailResponseDto getRoomPostDetail(Long userId, Long roomPostId);
}
