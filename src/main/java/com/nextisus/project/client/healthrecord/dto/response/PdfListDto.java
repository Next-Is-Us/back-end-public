package com.nextisus.project.client.healthrecord.dto.response;

import com.nextisus.project.domain.Condition;
import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@Setter
@Builder
public class PdfListDto {
    Long year;
    Long month;
    Long day;
    String sleepTime;
    Boolean isBlushing;
    Boolean isHeadache;
    Boolean isStomachache;
    Boolean isConstipated;
    Boolean isMusclePainful;
    Boolean isSkinTroubled;
    Boolean isNumbness;
    Boolean isChilled;
    Boolean isDepressed;
    String record;

    public static PdfListDto fromPdf (Condition condition) {
        return PdfListDto.builder()
                .year(condition.getYear())
                .month(condition.getMonth())
                .day(condition.getDay())
                .sleepTime(condition.getSleepTime())
                .isBlushing(condition.getIsBlushing())
                .isHeadache(condition.getIsHeadache())
                .isStomachache(condition.getIsStomachache())
                .isConstipated(condition.getIsConstipated())
                .isMusclePainful(condition.getIsMusclePainful())
                .isSkinTroubled(condition.getIsSkinTroubled())
                .isNumbness(condition.getIsNumbness())
                .isChilled(condition.getIsChilled())
                .isDepressed(condition.getIsDepressed())
                .record(condition.getRecord())
                .build();
    }

    public static PdfListDto from (Condition condition) {
        return fromPdf(condition);
    }
}
