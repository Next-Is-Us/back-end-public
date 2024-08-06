package com.nextisus.project.admin.controller;

import com.nextisus.project.admin.dto.CreateInfoPostRequestDto;
import com.nextisus.project.admin.dto.CreateInfoPostResponseDto;
import com.nextisus.project.admin.service.AdminService;
import com.nextisus.project.util.auth.AuthUtil;
import com.nextisus.project.util.response.SuccessResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {

    private final AuthUtil authUtil;
    private final AdminService adminService;

    // 관리자 accessToken 발급
    // 모두 접근 가능하지만 @RequestBody 에 nickname 값을 올바르게 입력해야 함
//    @PostMapping("/accessToken")
//    public SuccessResponse<CreateAccessTokenResponseDto> createAccessToken(@RequestBody CreateAccessTokenRequestDto dto) {
//        CreateAccessTokenResponseDto res = adminService.createAccessToken(dto);
//        return SuccessResponse.of(res);
//    }

    // 관리자가 InfoPost 생성
    @PostMapping(value = "/createPost", consumes = {"multipart/form-data"})
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public SuccessResponse<CreateInfoPostResponseDto> createInfoPost(@Valid @ModelAttribute CreateInfoPostRequestDto dto) {
        Long userId = Long.parseLong(authUtil.getCurrentUserId());
        CreateInfoPostResponseDto res = adminService.createInfoPost(dto, userId);
        return SuccessResponse.of(res);
    }
}
