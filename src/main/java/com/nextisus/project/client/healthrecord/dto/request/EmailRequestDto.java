package com.nextisus.project.client.healthrecord.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailRequestDto {
    @NotNull
    private String email;
    @NotNull
    private String pdfUrl;
}
