package com.nextisus.project.doctor.roompost.controller;

import com.nextisus.project.doctor.roompost.dto.CreateRoomPostRequestDto;
import com.nextisus.project.doctor.roompost.dto.CreateRoomPostResponseDto;
import com.nextisus.project.doctor.roompost.service.DoctorRoomPostService;
import com.nextisus.project.util.auth.AuthUtil;
import com.nextisus.project.util.response.SuccessResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/roomPost")
@RequiredArgsConstructor
public class DoctorRoomPostController {

    private final AuthUtil authUtil;
    private final DoctorRoomPostService doctorRoomPostService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_DOCTOR', 'ROLE_ADMIN')")
    public SuccessResponse<CreateRoomPostResponseDto> createRoomPost(@RequestBody @Valid CreateRoomPostRequestDto dto) {
        long userId = Long.parseLong(authUtil.getCurrentUserId());
        CreateRoomPostResponseDto res = doctorRoomPostService.createRoomPost(userId, dto);
        return SuccessResponse.of(res);
    }
}
