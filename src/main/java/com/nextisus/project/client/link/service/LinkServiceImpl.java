package com.nextisus.project.client.link.service;

import com.nextisus.project.client.link.dto.LinkResponseDto;
import com.nextisus.project.domain.Link;
import com.nextisus.project.repository.LinkRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LinkServiceImpl implements LinkService{

    private final LinkRepository linkRepository;

    @Override
    public LinkResponseDto link() {

        UUID url = UUID.randomUUID();
        log.info(String.valueOf(url));

        Link link = linkRepository.save(Link.toEntity(url.toString()));
        return LinkResponseDto.of(link.getLink());
    }

    @Override
    public void isLinkExist(String linkUrl) {
        linkRepository.getByLink(linkUrl);
    }

}
