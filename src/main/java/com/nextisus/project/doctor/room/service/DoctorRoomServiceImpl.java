package com.nextisus.project.doctor.room.service;

import com.nextisus.project.doctor.room.dto.CreateRoomRequestDto;
import com.nextisus.project.doctor.room.dto.CreateRoomResponseDto;
import com.nextisus.project.domain.Room;
import com.nextisus.project.domain.User;
import com.nextisus.project.domain.UserRoom;
import com.nextisus.project.exception.room.RoomNameDuplicatedException;
import com.nextisus.project.image.service.S3UploadService;
import com.nextisus.project.repository.RoomRepository;
import com.nextisus.project.repository.UserRepository;
import com.nextisus.project.repository.UserRoomRepository;
import jakarta.transaction.Transactional;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class DoctorRoomServiceImpl implements DoctorRoomService {

    private final RoomRepository roomRepository;
    private final UserRoomRepository userRoomRepository;
    private final UserRepository userRepository;
    private final S3UploadService s3UploadService;

    @Transactional
    @Override
    public CreateRoomResponseDto createRoom(CreateRoomRequestDto dto, Long userId) {
        try {
            // 사용자 존재하는지 확인
            User user = userRepository.getByUser(userId);

            // 방 이름 중복 확인
            roomRepository.findByName(dto.getName())
                    .ifPresent(room -> {
                        throw new RoomNameDuplicatedException();
                    });

            // thumbnail이 있는 경우에만 이미지 주소 변환
            String thumbnailPath = null;
            MultipartFile thumbnail = dto.getThumbnail();
            if (thumbnail != null && !thumbnail.isEmpty()) {
                thumbnailPath = s3UploadService.upload(thumbnail, "room-thumbnail");
            }

            // 생성 및 저장
            Room room = roomRepository.save(Room.toEntity(dto, thumbnailPath));

            // 사용자와 룸 간의 연관관계 설정
            UserRoom userRoom = new UserRoom(user, room);
            user.addUserRoom(userRoom);
            room.addUserRoom(userRoom);

            // UserRoom 저장
            userRoomRepository.save(userRoom);
            return CreateRoomResponseDto.builder().roomId(room.getId()).build();
        } catch (DataAccessException | RoomNameDuplicatedException e) {
            throw e; // DataAccessException 및 RoomNameDuplicatedException을 그대로 던지도록 수정
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload thumbnail", e); // IOException을 처리하여 RuntimeException으로 래핑
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error occurred", e); // 기타 예외 처리
        }

    }
}
