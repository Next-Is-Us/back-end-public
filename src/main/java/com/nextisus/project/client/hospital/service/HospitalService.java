package com.nextisus.project.client.hospital.service;

import com.nextisus.project.client.hospital.dto.HospitalListResponseDto;

import java.util.List;

public interface HospitalService {
    List<HospitalListResponseDto> getSearchHospitals(String keyword);
}
