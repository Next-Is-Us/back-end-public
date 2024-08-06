package com.nextisus.project.exception.ntf;

import com.nextisus.project.util.exception.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.nextisus.project.util.constant.StaticValue.NOT_FOUND;

@Getter
@AllArgsConstructor
public enum NftErrorCode implements BaseErrorCode {

    NFT_NOT_FOUND_1(NOT_FOUND,"NFT_404_1","존재하지 않는 nftId입니다."),
    NFT_NOT_FOUND_2(NOT_FOUND,"NFT_404_2","해당 유저에게 발급된 NFT가 존재하지 않습니다.");

    private final int httpStatus;
    private final String code;
    private final String message;
}
