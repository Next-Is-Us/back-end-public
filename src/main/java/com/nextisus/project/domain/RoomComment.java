package com.nextisus.project.domain;

import com.nextisus.project.util.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomComment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long commentId;

    @Column(name = "comment_content")
    private String commentContent;

    @ManyToOne
    @JoinColumn(name="room_post_id")
    private RoomPost roomPost;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public void setUser (User user, RoomPost roomPost) {
        this.user = user;
        this.roomPost = roomPost;
    }
}
