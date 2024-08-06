package com.nextisus.project.client.healthrecord.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreatePdfResponseDto {
    private String pdfUrl;

    public static CreatePdfResponseDto of(String pdfUrl) {
        return CreatePdfResponseDto.builder().pdfUrl(pdfUrl).build();
    }
}
