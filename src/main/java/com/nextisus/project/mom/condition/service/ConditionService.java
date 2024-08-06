package com.nextisus.project.mom.condition.service;

import com.nextisus.project.mom.condition.dto.request.CreateConditionRequestDto;
import com.nextisus.project.client.condition.dto.response.ConditionListResponseDto;
import com.nextisus.project.client.condition.dto.response.ConditionListResponseDtoByDate;
import com.nextisus.project.util.response.SuccessResponse;

public interface ConditionService {

    SuccessResponse<?> createCondition(CreateConditionRequestDto request, Long userId);

    ConditionListResponseDtoByDate getConditionByDate(Long year, Long month, Long day, Long userId,String userRole);

    ConditionListResponseDto getDetailConditionByDate(Long year, Long month, Long day, String userRole, Long userId);
}
