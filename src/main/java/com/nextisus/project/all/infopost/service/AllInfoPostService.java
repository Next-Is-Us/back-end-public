package com.nextisus.project.all.infopost.service;

import com.nextisus.project.all.infopost.dto.GetDetailInfoPostResDto;
import com.nextisus.project.all.infopost.dto.GetListInfoPostResDto;
import com.nextisus.project.util.response.PageResponse;
import org.springframework.data.domain.Pageable;

public interface AllInfoPostService {

    PageResponse<GetListInfoPostResDto> getListInfoPost(Pageable pageable);
    GetDetailInfoPostResDto getDetailInfoPost(Long infoPostId);
}
