package com.nextisus.project.client.healthrecord.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class EmailMessageDto {
    private String to; // 수신자
    private String subject; // 메일 제목
    private String content; // 메일 내용
}
