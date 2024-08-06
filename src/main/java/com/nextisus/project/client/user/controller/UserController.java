package com.nextisus.project.client.user.controller;

import com.nextisus.project.client.user.dto.SignUpRequestDto;
import com.nextisus.project.client.user.dto.SignUpResponseDto;
import com.nextisus.project.client.user.service.UserService;
import com.nextisus.project.util.response.SuccessResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController implements UserControllerApi {

    /**
     * 엄마와 자녀가 공통으로 사용할 수 있는 API 작성
     * ex) 회원가입, 로그인, 로그아웃 등
     */

    private final UserService userService;

    /**
     * 회원가입
     */
    @PostMapping("/signUp")
    public SuccessResponse<SignUpResponseDto> signUp(@RequestBody @Valid SignUpRequestDto dto) {
        SignUpResponseDto res = userService.signUp(dto);
        return SuccessResponse.of(res);
    }

}
