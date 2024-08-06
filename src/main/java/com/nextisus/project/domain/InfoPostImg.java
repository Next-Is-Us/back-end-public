package com.nextisus.project.domain;

import com.nextisus.project.util.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="INFOPOSTIMG")
public class InfoPostImg extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IMAGE_ID")
    private Long imageId;

    @Column(name="URL")
    private String url;

    @ManyToOne
    @JoinColumn(name="INFO_POST")
    private InfoPost infoPost;

    public void setPost(InfoPost infoPost) {
        this.infoPost = infoPost;
    }
}
