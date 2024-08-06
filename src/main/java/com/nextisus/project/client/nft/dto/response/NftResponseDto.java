package com.nextisus.project.client.nft.dto.response;

import com.nextisus.project.domain.Nft;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NftResponseDto {
    Long pieceOfNft;
    int totalNftCount;

    public static NftResponseDto from(Long pieceOfNft, int totalNftCount) {
        return new NftResponseDto(
                pieceOfNft,
                totalNftCount
        );
    }
}
