package com.nextisus.project.all.accesstoken.service;

import com.nextisus.project.all.accesstoken.dto.CreateAccessTokenRequestDto;
import com.nextisus.project.all.accesstoken.dto.CreateAccessTokenResponseDto;

public interface AccessTokenService {
    CreateAccessTokenResponseDto createAccessToken(CreateAccessTokenRequestDto dto);
}
