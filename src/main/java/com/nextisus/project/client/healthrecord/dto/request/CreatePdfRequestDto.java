package com.nextisus.project.client.healthrecord.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreatePdfRequestDto {
    @NotNull(message = "pdf 파일을 넣어주세요")
    MultipartFile pdfFile;
}
