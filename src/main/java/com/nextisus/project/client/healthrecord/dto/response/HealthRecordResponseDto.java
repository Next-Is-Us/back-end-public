package com.nextisus.project.client.healthrecord.dto.response;

import com.nextisus.project.domain.HealthRecord;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HealthRecordResponseDto {
    Long healthRecordId;
    String recordPeriod;
    String week;

    public static HealthRecordResponseDto from(HealthRecord healthRecord) {
        return new HealthRecordResponseDto(
                healthRecord.getHealthRecordId(),
                healthRecord.getRecordPeriod(),
                healthRecord.getWeek()
        );
    }
}
