package com.nextisus.project.doctor.roompost.service;

import com.nextisus.project.doctor.roompost.dto.CreateRoomPostRequestDto;
import com.nextisus.project.doctor.roompost.dto.CreateRoomPostResponseDto;
import com.nextisus.project.domain.Room;
import com.nextisus.project.domain.RoomPost;
import com.nextisus.project.domain.User;
import com.nextisus.project.repository.RoomPostRepository;
import com.nextisus.project.repository.RoomRepository;
import com.nextisus.project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DoctorRoomPostServiceImpl implements DoctorRoomPostService {

    private final UserRepository userRepository;
    private final RoomPostRepository roomPostRepository;
    private final RoomRepository roomRepository;

    @Override
    @Transactional
    public CreateRoomPostResponseDto createRoomPost(Long userId, CreateRoomPostRequestDto dto) {

        // [1-1] User 가져오기
        User user = userRepository.getByUser(userId);

        // [1-2] Room 가져오기
        Room room = roomRepository.getById(dto.getRoomId());

        // [2] 엔티티 생성
        RoomPost roomPost = roomPostRepository.save(RoomPost.toEntity(user, room, dto));

        // [3] 응답
        return CreateRoomPostResponseDto.of(roomPost);
    }
}
