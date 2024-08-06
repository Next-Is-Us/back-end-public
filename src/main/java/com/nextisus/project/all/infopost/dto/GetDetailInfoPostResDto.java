package com.nextisus.project.all.infopost.dto;

import static com.nextisus.project.util.format.CreateAtFormat.formatToDate;

import com.nextisus.project.domain.InfoPost;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GetDetailInfoPostResDto {
    private String title;
    private String content;
    private String whenCreated;
    private List<String> imageUrl;

    public static GetDetailInfoPostResDto from(InfoPost infoPost, List<String> imageUrl) {
        return new GetDetailInfoPostResDto(
                infoPost.getTitle(),
                infoPost.getContent(),
                formatToDate(infoPost.getCreateAt()),
                imageUrl
        );
    }
}
