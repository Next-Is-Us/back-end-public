package com.nextisus.project.mom.condition.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateConditionRequestDto {

    @NotNull(message = "수면 시간을 선택해주세요")
    Long sleepTime;

    Boolean isBlushing;
    Boolean isHeadache;
    Boolean isStomachache;
    Boolean isConstipated;
    Boolean isMusclePainful;
    Boolean isSkinTroubled;
    Boolean isNumbness;
    Boolean isChilled;
    Boolean isDepressed;

    @Size(max = 300, message = "추가 기록은 최대 300자 입니다.")
    String record;
}
