package com.nextisus.project.mom.test.controller;

import com.nextisus.project.mom.test.service.MomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mom")
public class MomController {

    private final MomService momService;

    /**
     * 엄마만 사용할 수 있는 API 작성
     * ex) 꽃피 생성 API 등
     */

}
