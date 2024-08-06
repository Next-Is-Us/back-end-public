package com.nextisus.project.test.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@Tag(name = "테스트 용으로 만든 API", description = "모두 접근 가능")
public interface TestControllerApi {

    @Operation(summary = "[GET 테스트] 간단한 테스트용 API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = "GET SUCCESS"),
                            schema = @Schema(implementation = String.class))),
    })
    @GetMapping
    ResponseEntity<String> getTest();
}
