package com.nextisus.project.mom.condition.controller;

import com.nextisus.project.mom.condition.dto.request.CreateConditionRequestDto;
import com.nextisus.project.mom.condition.service.ConditionService;
import com.nextisus.project.util.response.SuccessResponse;
import com.nextisus.project.util.auth.AuthUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/condition")
@PreAuthorize("hasAnyRole('ROLE_MOM')")
public class MomConditionController {

    private final AuthUtil authUtil;
    private final ConditionService conditionService;

    // 오늘의 상태 기록
    @PostMapping
    public SuccessResponse<?> createCondition(@Valid @RequestBody CreateConditionRequestDto request) {
        Long userId = Long.parseLong(authUtil.getCurrentUserId());
        conditionService.createCondition(request, userId);
        return SuccessResponse.empty();
    }
}
