package com.nextisus.project.client.mypage.dto;

import com.nextisus.project.domain.RoleName;
import com.nextisus.project.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GetMyFamilyInformationResponseDto {
    private String roleName;
    private String nickname;

    public static GetMyFamilyInformationResponseDto from(User user) {
        String roleName = user.getUserRoles().stream()
                .map(userRole -> userRole.getRole().getRoleName())
                .filter(role -> role == RoleName.ROLE_MOM || role == RoleName.ROLE_DAUGHTER || role == RoleName.ROLE_SON)
                .findFirst()
                .map(RoleName::name)
                .orElse(null);

        return GetMyFamilyInformationResponseDto.builder()
                .nickname(user.getNickname())
                .roleName(roleName)
                .build();
    }
}
