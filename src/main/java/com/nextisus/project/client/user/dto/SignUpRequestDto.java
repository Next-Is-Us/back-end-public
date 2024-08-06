package com.nextisus.project.client.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;
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
public class SignUpRequestDto {

    @NotNull
    @Schema(description = "사용자 역할 목록", example = "[\"ROLE_SON\"]", allowableValues = {"ROLE_SON", "ROLE_DAUGHTER", "ROLE_MOM", "ROLE_ADMIN", "ROLE_DOCTOR"})
    private List<String> userRoles;

    @NotNull @NotEmpty @NotBlank
    @Schema(description = "사용자 닉네임", example = "엄마아들")
    private String nickname;

    @NotNull @NotEmpty @NotBlank
    @Schema(description = "링크", example = "a890e53e-7409-4945-bc57-054aea519aca")
    private String link;
}
