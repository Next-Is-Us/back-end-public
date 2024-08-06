package com.nextisus.project.client.mypage.service;

import com.nextisus.project.client.mypage.dto.GetLinkResponseDto;
import com.nextisus.project.client.mypage.dto.GetMyFamilyInformationResponseDto;
import com.nextisus.project.client.mypage.dto.GetMyNicknameResponseDto;
import java.util.List;

public interface ClientMyPageService {
    GetMyNicknameResponseDto getMyNickname(Long userId);
    List<GetMyFamilyInformationResponseDto> getMyFamilyInformation(Long userId);
    GetLinkResponseDto getLink(Long userId);
}
