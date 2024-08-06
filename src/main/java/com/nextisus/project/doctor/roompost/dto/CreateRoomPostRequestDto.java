package com.nextisus.project.doctor.roompost.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateRoomPostRequestDto {
    @NotNull(message = "roomId는 필수값입니다.")
    private Long roomId;
    @NotNull(message = "title은 필수값입니다.")
    private String title;
    @NotNull(message = "content는 필수값입니다.")
    private String content;
}
