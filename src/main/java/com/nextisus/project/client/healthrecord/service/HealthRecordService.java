package com.nextisus.project.client.healthrecord.service;

import com.nextisus.project.client.healthrecord.dto.request.CreatePdfRequestDto;
import com.nextisus.project.client.healthrecord.dto.response.CreatePdfResponseDto;
import com.nextisus.project.client.healthrecord.dto.response.HealthRecordListDto;
import com.nextisus.project.client.healthrecord.dto.response.HealthRecordResponseDto;
import com.nextisus.project.client.healthrecord.dto.response.PdfListDto;
import com.nextisus.project.domain.HealthRecord;
import com.nextisus.project.util.response.PageResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface HealthRecordService {
    List<HealthRecordListDto> getHealthRecord(Long userId, String userRole);
    HealthRecord createHealthRecord(Long userId,Long countNft);
    HealthRecordResponseDto getHealthRecordDetail(Long healthRecordId);

    PageResponse<PdfListDto> getHealthRecordPdf(Long healthRecordId, Long userId, Pageable pageable);

    CreatePdfResponseDto savePdf(CreatePdfRequestDto createPdfRequestDto);
}

