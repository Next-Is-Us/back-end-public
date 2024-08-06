package com.nextisus.project.client.condition.controller;

import com.nextisus.project.client.condition.dto.response.ConditionListResponseDto;
import com.nextisus.project.client.condition.dto.response.ConditionListResponseDtoByDate;
import com.nextisus.project.mom.condition.service.ConditionService;
import com.nextisus.project.util.auth.AuthUtil;
import com.nextisus.project.util.response.PageResponse;
import com.nextisus.project.util.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/condition")
@PreAuthorize("hasAnyRole('ROLE_MOM', 'ROLE_SON', 'ROLE_DAUGHTER')")
public class ClientConditionController {

    private final AuthUtil authUtil;
    private final ConditionService conditionService;

    // 날짜 별 상태 여부 조회
    @GetMapping("/byDate/{year}/{month}/{day}/{userRole}")
    public SuccessResponse<ConditionListResponseDtoByDate> getConditionByDate(
            @PathVariable Long year,
            @PathVariable Long month,
            @PathVariable Long day,
            @PathVariable String userRole
    ) {
        Long userId = Long.parseLong(authUtil.getCurrentUserId());
        ConditionListResponseDtoByDate response = conditionService.getConditionByDate(year,month,day,userId, userRole);
        return SuccessResponse.of(response);
    }

    // 날짜 별 상태 기록 세부 조회
    @GetMapping("/detail/{year}/{month}/{day}/{userRole}")
    public SuccessResponse<ConditionListResponseDto> getDetailConditionByDate(
            @PathVariable Long year,
            @PathVariable Long month,
            @PathVariable Long day,
            @PathVariable String userRole
    ) {
        Long userId = Long.parseLong(authUtil.getCurrentUserId());
        ConditionListResponseDto response = conditionService.getDetailConditionByDate(year, month, day, userRole, userId);
        return SuccessResponse.of(response);
    }
}
