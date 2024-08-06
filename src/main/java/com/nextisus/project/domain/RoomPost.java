package com.nextisus.project.domain;

import com.nextisus.project.doctor.roompost.dto.CreateRoomPostRequestDto;
import com.nextisus.project.exception.roompost.RoomPostCreateForbiddenException;
import com.nextisus.project.util.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ROOM_POSTS")
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class RoomPost extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Column(name = "view_count")
    @Builder.Default
    private Integer viewCount = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public static RoomPost toEntity(User user, Room room, CreateRoomPostRequestDto dto) {
        checkWriteRoomPostAuthority(user, room);
        return RoomPost.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .user(user)
                .room(room)
                .build();
    }

    // 의사가 해당 방에 글을 작성할 권한이 있는지 확인
    private static void checkWriteRoomPostAuthority(User user, Room room) {
        Optional<UserRoom> userRoom = user.getUserRooms().stream()
                .filter(ur -> ur.getRoom().equals(room))
                .findFirst();

        if (userRoom.isEmpty()) {
            throw new RoomPostCreateForbiddenException();
        }
    }

    public void execute() {
        this.viewCount++;
    }

}
