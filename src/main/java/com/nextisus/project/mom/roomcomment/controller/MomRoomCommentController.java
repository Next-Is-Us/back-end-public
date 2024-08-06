package com.nextisus.project.mom.roomcomment.controller;

import com.nextisus.project.mom.roomcomment.dto.CreateCommentRequestDto;
import com.nextisus.project.mom.roomcomment.dto.RoomCommentListDto;
import com.nextisus.project.mom.roomcomment.service.MomRoomCommentService;
import com.nextisus.project.mom.roomcomment.service.MomRoomCommentServiceImpl;
import com.nextisus.project.util.auth.AuthUtil;
import com.nextisus.project.util.response.PageResponse;
import com.nextisus.project.util.response.SuccessResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roomComment")
@RequiredArgsConstructor
public class MomRoomCommentController {

    private final AuthUtil authUtil;
    private final MomRoomCommentService momRoomCommentService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_MOM', 'ROLE_DOCTOR', 'ROLE_ADMIN')")
    public SuccessResponse<?> createComment(@RequestBody @Valid CreateCommentRequestDto dto) {
        Long userId = Long.parseLong(authUtil.getCurrentUserId());
        momRoomCommentService.createComment(dto,userId);
        return SuccessResponse.empty();
    }

    @GetMapping("/all/{roomPostId}")
    @PreAuthorize("hasAnyRole('ROLE_MOM', 'ROLE_DOCTOR', 'ROLE_ADMIN')")
    public SuccessResponse<PageResponse<RoomCommentListDto>> getComments(
            @PathVariable Long roomPostId,
            @PageableDefault(size=10) Pageable pageable
    ) {
        PageResponse<RoomCommentListDto> response = momRoomCommentService.getComments(roomPostId, pageable);
        return SuccessResponse.of(response);
    }

}
