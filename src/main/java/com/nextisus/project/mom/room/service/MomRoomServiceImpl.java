package com.nextisus.project.mom.room.service;

import com.nextisus.project.domain.Room;
import com.nextisus.project.domain.User;
import com.nextisus.project.domain.UserRoom;
import com.nextisus.project.exception.room.RoomNftNotEnough;
import com.nextisus.project.mom.room.dto.EnterRoomRequestDto;
import com.nextisus.project.mom.room.dto.GetRoomDetailResponseDto;
import com.nextisus.project.mom.room.dto.GetRoomListResponseDto;
import com.nextisus.project.repository.NftRepository;
import com.nextisus.project.repository.RoomRepository;
import com.nextisus.project.repository.UserRepository;
import com.nextisus.project.repository.UserRoomRepository;
import com.nextisus.project.util.response.PageResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MomRoomServiceImpl implements MomRoomService {

    private final RoomRepository roomRepository;
    private final NftRepository nftRepository;
    private final UserRepository userRepository;
    private final UserRoomRepository userRoomRepository;


    @Override
    public void enterRoom(Long userId, EnterRoomRequestDto dto) {

        // [1-1] 유저가 가진 꽃피 개수
        Long numOfNftUserHas = nftRepository.countByUser(userRepository.getByUser(userId));
        log.info("[유저가 가진 꽃피 개수] " + numOfNftUserHas);

        // [1-2] 방에 입장하기 위한 꽃피 개수
        Long numOfNftToEnterRoom = roomRepository.getById(dto.getRoomId()).getNecessaryNftCount();
        log.info("[방에 입장하기 위한 꽃피 개수] " + numOfNftToEnterRoom);

        // [1-3] 소통방 입장 조건 확인
        if (numOfNftToEnterRoom > numOfNftUserHas) {
            throw new RoomNftNotEnough();
        }

        // [2] 유저 정보 가져오기
        User user = userRepository.getByUser(userId);
        Room room = roomRepository.getById(dto.getRoomId());

        // [3] 이미 입장해 있는지 확인
        boolean alreadyEntered = user.getUserRooms().stream()
                .anyMatch(userRoom -> userRoom.getRoom().equals(room));

        if (alreadyEntered) {
            log.info("이미 방에 입장해 있습니다.");
        } else {
            // [4] 입장하지 않았다면 연관관계 맺고 데이터 저장
            UserRoom userRoom = new UserRoom(user, room);
            user.addUserRoom(userRoom);
            room.addUserRoom(userRoom);

            // [5] Room의 peopleCount 1 증가
            room.incrementPeopleCount();

            userRoomRepository.save(userRoom);
            log.info("방에 성공적으로 입장했습니다.");
        }
    }

    @Override
    public PageResponse<GetRoomListResponseDto> getRoomList(Long userId, Pageable pageable) {
        Long numOfNftUserHas = nftRepository.countByUser(userRepository.getByUser(userId));
        Page<GetRoomListResponseDto> rooms = roomRepository.findAllByOrderByCreateAtDesc(pageable)
                .map(room -> GetRoomListResponseDto.from(room, numOfNftUserHas));
        List<GetRoomListResponseDto> list = rooms.getContent();
        PageImpl<GetRoomListResponseDto> data = new PageImpl<>(list, pageable, rooms.getTotalElements());
        return PageResponse.of(data);
    }

    @Override
    public GetRoomDetailResponseDto getRoomDetail(Long roomId) {
        Room room = roomRepository.getById(roomId);
        return GetRoomDetailResponseDto.from(room);
    }
}
