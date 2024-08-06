package com.nextisus.project.client.user.service;

import com.nextisus.project.client.user.dto.SignUpRequestDto;
import com.nextisus.project.client.user.dto.SignUpResponseDto;

public interface UserService {
    SignUpResponseDto signUp (SignUpRequestDto dto);
}
