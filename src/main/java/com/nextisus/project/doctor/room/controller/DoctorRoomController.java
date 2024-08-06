package com.nextisus.project.doctor.room.controller;

import com.nextisus.project.doctor.room.dto.CreateRoomRequestDto;
import com.nextisus.project.doctor.room.dto.CreateRoomResponseDto;
import com.nextisus.project.doctor.room.service.DoctorRoomService;
import com.nextisus.project.util.auth.AuthUtil;
import com.nextisus.project.util.response.SuccessResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/doctor")
@PreAuthorize("hasAnyRole('ROLE_DOCTOR', 'ROLE_ADMIN')")
public class DoctorRoomController {

    private final AuthUtil authUtil;
    private final DoctorRoomService doctorRoomService;

    @PostMapping(value="/createRoom", consumes = {"multipart/form-data"})
    public SuccessResponse<CreateRoomResponseDto> createRoom(@Valid @ModelAttribute CreateRoomRequestDto dto) {
        Long userId = Long.parseLong(authUtil.getCurrentUserId());
        CreateRoomResponseDto res = doctorRoomService.createRoom(dto, userId);
        return SuccessResponse.of(res);
    }
}
