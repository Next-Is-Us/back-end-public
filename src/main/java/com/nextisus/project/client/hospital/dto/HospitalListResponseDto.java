package com.nextisus.project.client.hospital.dto;

import com.nextisus.project.domain.Hospital;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HospitalListResponseDto {
    Long hospitalId;
    String hospitalName ;
    String hospitalAddress ;
    String hospitalTel ;
    public static HospitalListResponseDto from(Hospital hospital) {
        return new HospitalListResponseDto(
                hospital.getHospitalId(),
                hospital.getHospitalName(),
                hospital.getHospitalAddress(),
                hospital.getHospitalTel()
        );
    }
}
