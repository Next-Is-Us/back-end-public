package com.nextisus.project.all.accesstoken.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreateAccessTokenResponseDto {
    private String accessToken;
}
