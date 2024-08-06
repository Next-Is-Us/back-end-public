package com.nextisus.project.doctor.room.service;

import com.nextisus.project.doctor.room.dto.CreateRoomRequestDto;
import com.nextisus.project.doctor.room.dto.CreateRoomResponseDto;

public interface DoctorRoomService {
    CreateRoomResponseDto createRoom(CreateRoomRequestDto dto, Long userId);
}
