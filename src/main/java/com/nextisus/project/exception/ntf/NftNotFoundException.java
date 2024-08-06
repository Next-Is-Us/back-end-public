package com.nextisus.project.exception.ntf;

import com.nextisus.project.util.exception.BaseException;

public class NftNotFoundException extends BaseException {
    public NftNotFoundException() {
        super(NftErrorCode.NFT_NOT_FOUND_1);
    }
}
