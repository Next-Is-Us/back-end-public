package com.nextisus.project.client.link.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LinkResponseDto {
    private String link;

    public static LinkResponseDto of(String link) {
        return LinkResponseDto.builder()
                .link(link)
                .build();
    }
}
