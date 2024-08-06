package com.nextisus.project.domain;

import com.nextisus.project.admin.dto.CreateInfoPostRequestDto;
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
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 건강정보 게시글
 * [ROLE_ADMIN] 생성/수정/삭제 가능
 * [ROLE_SON/ROLE_DAUGHTER/ROLE_DOCTOR] 조회 가능
 */
@Entity
@Table(name = "INFOPOSTS")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class InfoPost extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "info_post_title")
    private String title;

    @Column(name = "info_post_content", columnDefinition = "TEXT")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name="thumbnail")
    private String thumbnail;

    public static InfoPost toEntity(CreateInfoPostRequestDto dto, User user, String thumbnail) {
        return InfoPost.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .user(user)
                .thumbnail(thumbnail)
                .build();
    }
}
