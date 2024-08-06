package com.nextisus.project.exception.link;

import com.nextisus.project.util.exception.BaseException;

public class LinkNotFoundException extends BaseException {
    public LinkNotFoundException() {
        super(LinkErrorCode.LINK_NOT_FOUND);
    }
}
