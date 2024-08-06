package com.nextisus.project.client.link.service;

import com.nextisus.project.client.link.dto.LinkResponseDto;

public interface LinkService {
    LinkResponseDto link();
    void isLinkExist(String linkUrl);
}
