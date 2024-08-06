package com.nextisus.project.admin.service;

import com.nextisus.project.admin.dto.CreateInfoPostRequestDto;
import com.nextisus.project.admin.dto.CreateInfoPostResponseDto;

public interface AdminService {
    CreateInfoPostResponseDto createInfoPost(CreateInfoPostRequestDto dto, Long id);
}
