package com.nextisus.project.client.hospital.service;

import com.nextisus.project.client.hospital.dto.HospitalListResponseDto;
import com.nextisus.project.domain.Hospital;
import com.nextisus.project.repository.HospitalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HospitalServiceImpl implements HospitalService {

    private final HospitalRepository hospitalRepository;

    //병원 검색 기능
    @Override
    public List<HospitalListResponseDto> getSearchHospitals(String keyword) {

        // 검색한 키워드가 포함된 병원이름 리스트 받아오기
        List<Hospital> allByHospitalNameContaining = hospitalRepository.findAllByHospitalNameContaining(keyword);
        List<HospitalListResponseDto> responseDto = new ArrayList<>();

        /**
         * 두 번 호출되는거 해결되면 빼도 됨
         */
        if(allByHospitalNameContaining.size() == 1000) {
            responseDto.add(null);
        }

        else {
            for(Hospital hospital : allByHospitalNameContaining) {
                responseDto.add(HospitalListResponseDto.from(hospital));
            }
        }
        return  responseDto;
    }
}
