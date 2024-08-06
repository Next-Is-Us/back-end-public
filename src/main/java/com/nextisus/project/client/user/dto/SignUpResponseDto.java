package com.nextisus.project.client.user.dto;

import com.nextisus.project.client.mypage.dto.GetMyFamilyInformationResponseDto;
import com.nextisus.project.domain.RoleName;
import com.nextisus.project.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpResponseDto {
    private Long id;
    private String roleName;
    private String accessToken;

    public static SignUpResponseDto from(User user, String accessToken) {
        String roleName = user.getUserRoles().stream()
                .map(userRole -> userRole.getRole().getRoleName())
                .filter(role -> role == RoleName.ROLE_MOM || role == RoleName.ROLE_DAUGHTER || role == RoleName.ROLE_SON)
                .findFirst()
                .map(RoleName::name)
                .orElse(null);

        return SignUpResponseDto.builder()
                .id(user.getId())
                .roleName(roleName)
                .accessToken(accessToken)
                .build();
    }
}
