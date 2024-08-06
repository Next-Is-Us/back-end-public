package com.nextisus.project.doctor.room.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Builder
public class CreateRoomRequestDto {
    @NotNull
    private String name;
    @Nullable
    private String introduction;
    @Nullable
    private Long necessaryNftCount;
    @Nullable
    private MultipartFile thumbnail;
}
