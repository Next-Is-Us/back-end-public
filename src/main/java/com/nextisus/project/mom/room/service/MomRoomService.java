package com.nextisus.project.mom.room.service;

import com.nextisus.project.mom.room.dto.EnterRoomRequestDto;
import com.nextisus.project.mom.room.dto.GetRoomDetailResponseDto;
import com.nextisus.project.mom.room.dto.GetRoomListResponseDto;
import com.nextisus.project.util.response.PageResponse;
import org.springframework.data.domain.Pageable;

public interface MomRoomService {
    void enterRoom(Long userId, EnterRoomRequestDto dto);
    PageResponse<GetRoomListResponseDto> getRoomList(Long userId, Pageable pageable);
    GetRoomDetailResponseDto getRoomDetail(Long roomId);
}
