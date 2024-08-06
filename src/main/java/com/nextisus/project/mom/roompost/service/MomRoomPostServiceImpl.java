package com.nextisus.project.mom.roompost.service;

import com.nextisus.project.domain.Room;
import com.nextisus.project.domain.RoomPost;
import com.nextisus.project.domain.User;
import com.nextisus.project.exception.roompost.RoomPostCreateForbiddenException;
import com.nextisus.project.mom.roompost.dto.GetRoomPostDetailResponseDto;
import com.nextisus.project.mom.roompost.dto.GetRoomPostListResponseDto;
import com.nextisus.project.repository.RoomCommentRepository;
import com.nextisus.project.repository.RoomPostRepository;
import com.nextisus.project.repository.RoomRepository;
import com.nextisus.project.repository.UserRepository;
import com.nextisus.project.util.response.PageResponse;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MomRoomPostServiceImpl implements MomRoomPostService {

    private final RoomRepository roomRepository;
    private final RoomPostRepository roomPostRepository;
    private final UserRepository userRepository;
    private final RoomCommentRepository roomCommentRepository;

    @Override
    public PageResponse<GetRoomPostListResponseDto> getRoomPostList(Long roomId, Pageable pageable) {
        roomRepository.getById(roomId);
        Page<GetRoomPostListResponseDto> roomPosts = roomPostRepository.findAllByRoomIdOrderByCreateAtDesc(roomId, pageable)
                .map(roomPost -> {
                    Long commentCount = roomCommentRepository.countByRoomPost_Id(roomPost.getId());
                    return GetRoomPostListResponseDto.from(roomPost, commentCount);
                });
        PageImpl<GetRoomPostListResponseDto> data = new PageImpl<>(roomPosts.getContent(), pageable, roomPosts.getTotalElements());
        return PageResponse.of(data);
    }

    @Override
    @Transactional
    public GetRoomPostDetailResponseDto getRoomPostDetail(Long userId, Long roomPostId) {
        User user = userRepository.getByUser(userId);
        RoomPost roomPost = roomPostRepository.getById(roomPostId);

        Room room = roomPost.getRoom();

        user.getUserRooms().stream()
                .filter(userRoom -> userRoom.getRoom().equals(room))
                .findFirst()
                .orElseThrow(RoomPostCreateForbiddenException::new);

        // 나중에..
//        // RoomPost가 Room에 포함되어 있는지 확인
//        boolean postInRoom = room.getRoomPosts().contains(roomPost);
//
//        if (!postInRoom) {
//            throw new RuntimeException("Room post does not belong to the specified room");
//        }

        roomPost.execute(); // 조회수 증가
        return GetRoomPostDetailResponseDto.from(roomPost);
    }
}
