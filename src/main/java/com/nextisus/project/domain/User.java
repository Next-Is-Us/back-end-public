package com.nextisus.project.domain;

import com.nextisus.project.client.user.dto.SignUpRequestDto;
import com.nextisus.project.util.entity.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "USERS",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"link_id", "nickname"})
        })
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nickname")
    private String nickname;

    @Builder.Default
    @Column(name = "is_notification_enabled")
    private Boolean isNotificationEnabled = true;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserRole> userRoles = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InfoPost> infoPosts = new ArrayList<>();

    @Setter
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserRoom> userRooms = new ArrayList<>();

    @Setter
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RoomPost> roomPosts = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "link_id")
    private Link link;

    public static User toEntity(SignUpRequestDto dto, List<Role> roles, Link link) {

        User user = User.builder()
                .nickname(dto.getNickname())
                .link(link)
                .build();

        List<UserRole> userRoles = dto.getUserRoles().stream()
                .map(roleName -> roles.stream()
                        .filter(role -> role.getRoleName().name().equals(roleName))
                        .findFirst()
                        .map(role -> new UserRole(null, user, role))
                        .orElseThrow(() -> new IllegalArgumentException(roleName + "는 존재하지 않는 역할입니다.")))
                .collect(Collectors.toList());

        user.userRoles = userRoles;

        return user;
    }

    public void addUserRole(UserRole userRole) {
        if (userRoles == null) {
            userRoles = new ArrayList<>();
        }
        this.userRoles.add(userRole);
        userRole.setUser(this); // 양방향 관계 설정
    }

    public void addUserRoom(UserRoom userRoom) {
        if (userRooms == null) {
            userRooms = new ArrayList<>();
        }
        this.userRooms.add(userRoom);
        userRoom.setUser(this); // 양방향 관계 설정
    }
}
