package com.nextisus.project.client.condition.dto.response;

import com.nextisus.project.domain.Condition;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConditionListResponseDto {
    String nickname;
    String date;
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

    public static ConditionListResponseDto from(Condition condition, String date){
        return new ConditionListResponseDto(
                condition.getUser().getNickname(),
                date,
                condition.getSleepTime(),
                condition.getIsBlushing(),
                condition.getIsHeadache(),
                condition.getIsStomachache(),
                condition.getIsConstipated(),
                condition.getIsMusclePainful(),
                condition.getIsSkinTroubled(),
                condition.getIsNumbness(),
                condition.getIsChilled(),
                condition.getIsDepressed(),
                condition.getRecord()
        );
    }
}
