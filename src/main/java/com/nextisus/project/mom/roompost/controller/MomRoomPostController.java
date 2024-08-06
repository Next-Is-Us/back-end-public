package com.nextisus.project.mom.roompost.controller;

import com.nextisus.project.mom.roompost.dto.GetRoomPostDetailResponseDto;
import com.nextisus.project.mom.roompost.dto.GetRoomPostListResponseDto;
import com.nextisus.project.mom.roompost.service.MomRoomPostService;
import com.nextisus.project.util.auth.AuthUtil;
import com.nextisus.project.util.response.PageResponse;
import com.nextisus.project.util.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/roomPost")
@RequiredArgsConstructor
public class MomRoomPostController {

    private final AuthUtil authUtil;
    private final MomRoomPostService momRoomPostService;

    @GetMapping("/list")
    @PreAuthorize("hasAnyRole('ROLE_MOM', 'ROLE_DOCTOR', 'ROLE_ADMIN')")
    public SuccessResponse<PageResponse<GetRoomPostListResponseDto>> getRoomPostList(
            @RequestParam Long roomId,
            @PageableDefault(size = 4) Pageable pageable
            ) {
        PageResponse<GetRoomPostListResponseDto> res = momRoomPostService.getRoomPostList(roomId, pageable);
        return SuccessResponse.of(res);
    }

    @GetMapping("/detail/{roomPostId}")
    @PreAuthorize("hasAnyRole('ROLE_MOM', 'ROLE_DOCTOR', 'ROLE_ADMIN')")
    public SuccessResponse<GetRoomPostDetailResponseDto> getRoomPostDetail(@PathVariable("roomPostId") Long roomPostId) {
        long userId = Long.parseLong(authUtil.getCurrentUserId());
        GetRoomPostDetailResponseDto res = momRoomPostService.getRoomPostDetail(userId, roomPostId);
        return SuccessResponse.of(res);
    }
}
