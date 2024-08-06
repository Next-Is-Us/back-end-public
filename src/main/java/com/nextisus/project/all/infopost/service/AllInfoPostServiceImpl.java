package com.nextisus.project.all.infopost.service;

import com.nextisus.project.all.infopost.dto.GetDetailInfoPostResDto;
import com.nextisus.project.all.infopost.dto.GetListInfoPostResDto;
import com.nextisus.project.domain.InfoPost;
import com.nextisus.project.domain.InfoPostImg;
import com.nextisus.project.repository.InfoPostImgRepository;
import com.nextisus.project.repository.InfoPostRepository;
import com.nextisus.project.util.response.PageResponse;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AllInfoPostServiceImpl implements AllInfoPostService {

    private final InfoPostRepository infoPostRepository;
    private final InfoPostImgRepository infoPostImgRepository;

    @Override
    public PageResponse<GetListInfoPostResDto> getListInfoPost(Pageable pageable) {
        Page<GetListInfoPostResDto> posts = infoPostRepository.findAllByOrderByCreateAtDesc(pageable).map(GetListInfoPostResDto::from);
        List<GetListInfoPostResDto> list = posts.getContent();
        PageImpl<GetListInfoPostResDto> data = new PageImpl<>(list, pageable, posts.getTotalElements());
        return PageResponse.of(data);
    }

    @Override
    public GetDetailInfoPostResDto getDetailInfoPost(Long infoPostId) {
        InfoPost infoPost = infoPostRepository.getByInfoPostId(infoPostId);
        List<InfoPostImg> infoPostImgs = infoPostImgRepository.findAllByInfoPost_Id(infoPostId);

        List<String> imgPath = new ArrayList<>();
        if(!infoPostImgs.isEmpty()){
            for(InfoPostImg image : infoPostImgs){
                imgPath.add(image.getUrl());
            }
        }
        return GetDetailInfoPostResDto.from(infoPost,imgPath);
    }
}
