package com.nextisus.project.mom.condition.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DateRequestDto {
    @NotNull(message = "조회할 날짜의 년도를 입력해주세요")
    Long year;
    @NotNull(message = "조회할 날짜가 몇월인지 입력해주세요")
    Long month;
    @NotNull(message = "조회할 날짜가 몇일인지 입력해주세요")
    Long day;
}
