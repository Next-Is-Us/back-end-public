package com.nextisus.project.admin.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@Builder
public class CreateInfoPostRequestDto {
    private String title;
    private String content;
    private List<MultipartFile> imageUrl;
}
