package com.nextisus.project.client.condition.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConditionListResponseDtoByDate {
    String date;
    Boolean isRecording;
    String nickname;
    Boolean isInvited;
    String link;
    String userRole;
}
