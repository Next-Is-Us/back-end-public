package com.nextisus.project.exception.ntf;

import com.nextisus.project.util.exception.BaseException;

public class UserNftNotFoundException extends BaseException {
    public UserNftNotFoundException() {
        super(NftErrorCode.NFT_NOT_FOUND_2);
    }
}
