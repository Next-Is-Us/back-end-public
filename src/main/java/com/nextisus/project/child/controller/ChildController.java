package com.nextisus.project.child.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/child")
@PreAuthorize("hasAnyRole('ROLE_SON', 'ROLE_DAUGHTER')")
public class ChildController {

    /**
     * 자녀만 사용할 수 있는 API 작성
     * ex) 엄마 꽃피 기록 확인 API 등
     */

    @GetMapping
    public void hello() {
        log.info("SON or DAUGHTER");
    }
}
