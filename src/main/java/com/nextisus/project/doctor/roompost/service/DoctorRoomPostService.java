package com.nextisus.project.doctor.roompost.service;

import com.nextisus.project.doctor.roompost.dto.CreateRoomPostRequestDto;
import com.nextisus.project.doctor.roompost.dto.CreateRoomPostResponseDto;

public interface DoctorRoomPostService {
    CreateRoomPostResponseDto createRoomPost(Long userId, CreateRoomPostRequestDto dto);
}
