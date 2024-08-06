package com.nextisus.project.client.mypage.dto;

import com.nextisus.project.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GetLinkResponseDto {
    private String link;

    public static GetLinkResponseDto of(User user) {
        return GetLinkResponseDto.builder()
                .link(user.getLink().getLink())
                .build();
    }
}
