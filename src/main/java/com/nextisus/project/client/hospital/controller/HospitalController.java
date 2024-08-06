package com.nextisus.project.client.hospital.controller;

import com.nextisus.project.client.hospital.dto.HospitalListResponseDto;
import com.nextisus.project.client.hospital.service.HospitalService;
import com.nextisus.project.util.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/hospital")
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('ROLE_MOM', 'ROLE_SON', 'ROLE_DAUGHTER')")
public class HospitalController {

    private final HospitalService hospitalService;

    @GetMapping("/search")
    public SuccessResponse<List<HospitalListResponseDto>> getSearchHospitals(@RequestParam(required = false) String keyword) {
        List<HospitalListResponseDto> response = hospitalService.getSearchHospitals(keyword);
        return SuccessResponse.of(response);
    }
}
