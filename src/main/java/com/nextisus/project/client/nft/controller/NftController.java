package com.nextisus.project.client.nft.controller;

import com.nextisus.project.client.nft.service.NftService;
import com.nextisus.project.client.nft.dto.response.NftResponseDto;
import com.nextisus.project.util.auth.AuthUtil;
import com.nextisus.project.util.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/nft")
@PreAuthorize("hasAnyRole('ROLE_MOM', 'ROLE_SON', 'ROLE_DAUGHTER')")
public class NftController {

    private final AuthUtil authUtil;
    private final NftService nftService;

    // nft 조회
    @GetMapping("/{userRole}")
    public SuccessResponse<NftResponseDto> getNfts(@PathVariable String userRole) {
        Long userId = Long.parseLong(authUtil.getCurrentUserId());
        NftResponseDto response = nftService.getNfts(userId,userRole);
        return SuccessResponse.of(response);
    }
}
