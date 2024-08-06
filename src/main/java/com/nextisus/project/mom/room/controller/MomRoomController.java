package com.nextisus.project.mom.room.controller;

import com.nextisus.project.mom.room.dto.EnterRoomRequestDto;
import com.nextisus.project.mom.room.dto.GetRoomDetailResponseDto;
import com.nextisus.project.mom.room.dto.GetRoomListResponseDto;
import com.nextisus.project.mom.room.service.MomRoomService;
import com.nextisus.project.util.auth.AuthUtil;
import com.nextisus.project.util.response.PageResponse;
import com.nextisus.project.util.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/room")
@RequiredArgsConstructor
public class MomRoomController {

    private final MomRoomService momRoomService;
    private final AuthUtil authUtil;

    @PostMapping("/enter")
    @PreAuthorize("hasAnyRole('ROLE_MOM', 'ROLE_ADMIN')")
    public SuccessResponse<?> enterRoom(@RequestBody EnterRoomRequestDto dto) {
        long userId = Long.parseLong(authUtil.getCurrentUserId());
        momRoomService.enterRoom(userId, dto);
        return SuccessResponse.empty();
    }

    @GetMapping("/list")
    @PreAuthorize("hasAnyRole('ROLE_MOM','ROLE_DOCTOR', 'ROLE_ADMIN')")
    public SuccessResponse<PageResponse<GetRoomListResponseDto>> getRoomList(
            @PageableDefault(size = 4, page = 0) Pageable pageable
            ) {
        long userId = Long.parseLong(authUtil.getCurrentUserId());
        PageResponse<GetRoomListResponseDto> res = momRoomService.getRoomList(userId, pageable);
        return SuccessResponse.of(res);
    }

    @GetMapping("detail/{roomId}")
    @PreAuthorize("hasAnyRole('ROLE_MOM', 'ROLE_DOCTOR', 'ROLE_ADMIN')")
    public SuccessResponse<GetRoomDetailResponseDto> getRoomDetail(@PathVariable("roomId") Long roomId) {
        GetRoomDetailResponseDto res = momRoomService.getRoomDetail(roomId);
        return SuccessResponse.of(res);
    }

}
