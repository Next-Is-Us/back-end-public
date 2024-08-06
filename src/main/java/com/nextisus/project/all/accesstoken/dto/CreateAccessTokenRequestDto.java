package com.nextisus.project.all.accesstoken.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAccessTokenRequestDto {
    @Nullable
    private String link;
    @NotNull
    private String nickname;
}
