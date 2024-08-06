package com.nextisus.project.client.healthrecord.dto.response;

import com.nextisus.project.domain.HealthRecord;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HealthRecordListDto {
    Long healthRecordId;
    String recordPeriod;
    String week;
    Long nftCount;
    Boolean isComplete;

    public static HealthRecordListDto from(HealthRecord healthRecord, Boolean isComplete) {
        return new HealthRecordListDto(
                healthRecord.getHealthRecordId(),
                healthRecord.getRecordPeriod(),
                healthRecord.getWeek(),
                healthRecord.getNftCount(),
                isComplete
        );
    }
}
