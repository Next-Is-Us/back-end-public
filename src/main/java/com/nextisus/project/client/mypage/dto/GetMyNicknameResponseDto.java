package com.nextisus.project.client.mypage.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GetMyNicknameResponseDto {
    private String nickname;

    public static GetMyNicknameResponseDto of(String nickname) {
        return GetMyNicknameResponseDto.builder()
                .nickname(nickname)
                .build();
    }
}
