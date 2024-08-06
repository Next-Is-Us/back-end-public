package com.nextisus.project.client.healthrecord.controller;

import com.nextisus.project.client.healthrecord.dto.request.CreatePdfRequestDto;
import com.nextisus.project.client.healthrecord.dto.request.EmailRequestDto;
import com.nextisus.project.client.healthrecord.dto.response.CreatePdfResponseDto;
import com.nextisus.project.client.healthrecord.dto.response.HealthRecordListDto;
import com.nextisus.project.client.healthrecord.dto.response.HealthRecordResponseDto;
import com.nextisus.project.client.healthrecord.dto.response.PdfListDto;
import com.nextisus.project.client.healthrecord.service.HealthRecordService;
import com.nextisus.project.util.auth.AuthUtil;
import com.nextisus.project.util.email.service.EmailService;
import com.nextisus.project.util.response.PageResponse;
import com.nextisus.project.util.response.SuccessResponse;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/healthRecord")
@RequiredArgsConstructor
@Slf4j
@PreAuthorize("hasAnyRole('ROLE_MOM', 'ROLE_SON', 'ROLE_DAUGHTER')")
public class HealthRecordController {

    private final AuthUtil authUtils;
    private final HealthRecordService healthRecordService;
    private final EmailService emailService;

    // 건강 기록 전체 조회
    @GetMapping
    public SuccessResponse<List<HealthRecordListDto>> getHealthRecord(@RequestParam String userRole) {
        Long userId = Long.parseLong(authUtils.getCurrentUserId());
        List<HealthRecordListDto> response = healthRecordService.getHealthRecord(userId, userRole);
        return SuccessResponse.of(response);
    }

    // 건강 기록 세부 조회 (카드 컴포넌트 클릭 시)
    @GetMapping("/detail/{healthRecordId}")
    public SuccessResponse<HealthRecordResponseDto> getHealthRecordDetail(@PathVariable Long healthRecordId) {
        HealthRecordResponseDto response = healthRecordService.getHealthRecordDetail(healthRecordId);
        return SuccessResponse.of(response);
    }

    // PDF 파일로 변환 할 때 넣을 데이터 조회
    @GetMapping("/pdf/{healthRecordId}")
    public SuccessResponse<PageResponse<PdfListDto>> getHealthRecordPdf(
            @PathVariable Long healthRecordId,
            @PageableDefault(size = 10) Pageable pageable
    ) {
        Long userId = Long.parseLong(authUtils.getCurrentUserId());
        PageResponse<PdfListDto> response = healthRecordService.getHealthRecordPdf(healthRecordId, userId, pageable);
        return SuccessResponse.of(response);
    }

    // PDF 파일 저장
    @PostMapping(value = "/savePdf", consumes = {"multipart/form-data"})
    public SuccessResponse<CreatePdfResponseDto> savePdf(@Valid @ModelAttribute CreatePdfRequestDto createPdfRequestDto) {
        CreatePdfResponseDto res = healthRecordService.savePdf(createPdfRequestDto);
        return SuccessResponse.of(res);
    }

    @PostMapping("/sendEmail")
    public SuccessResponse<Void> sendEmail(@RequestBody @Valid EmailRequestDto dto) {
        long userId = Long.parseLong(authUtils.getCurrentUserId());
        emailService.sendPdf(dto, userId);
        return SuccessResponse.empty();
    }
}
