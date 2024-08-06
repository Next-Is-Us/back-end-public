package com.nextisus.project.client.nft.service;

import com.nextisus.project.client.nft.dto.response.NftResponseDto;
import com.nextisus.project.domain.Nft;

public interface NftService {
    NftResponseDto getNfts(Long userId, String userRole);
    Nft createNft(Long userId);
}
