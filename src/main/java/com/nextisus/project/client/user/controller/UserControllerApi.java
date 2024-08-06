package com.nextisus.project.client.user.controller;

import com.nextisus.project.client.user.dto.SignUpRequestDto;
import com.nextisus.project.client.user.dto.SignUpResponseDto;
import com.nextisus.project.util.response.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "유저 관련 API")
public interface UserControllerApi {

    @Operation(summary = "[ALL] 회원가입 API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = "{\n" +
                                "    \"timestamp\": \"2024-07-21T23:15:38.171915\",\n" +
                                "    \"isSuccess\": true,\n" +
                                "    \"code\": \"200\",\n" +
                                "    \"message\": \"호출에 성공하였습니다.\",\n" +
                                "    \"data\": {\n" +
                                "        \"id\": 1,\n" +
                                "        \"accessToken\": \"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwiYXV0aCI6WyJST0xFX1NPTiJdLCJpYXQiOjE3MjE2Mjg5MzgsImV4cCI6MTcyNDIyMDkzOH0.QhNwXMmIAsEYf_ZSd5s50UsPwkz3v4zdY7r8diX1iYI\"\n" +
                                "    }\n" +
                                "}"),
                            schema = @Schema(implementation = SuccessResponse.class))),
            @ApiResponse(responseCode = "400", description = "잘못된 요청",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = "{\n" +
                                "    \"timestamp\": \"2024-07-21T19:00:42.584282\",\n" +
                                "    \"isSuccess\": false,\n" +
                                "    \"code\": \"GLOBAL_400_1\",\n" +
                                "    \"message\": \"잘못된 요청입니다.\",\n" +
                                "    \"httpStatus\": 400\n" +
                                "}"),
                            schema = @Schema(implementation = SuccessResponse.class))),
            @ApiResponse(responseCode = "400", description = "값 누락",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = "{\n" +
                                "    \"timestamp\": \"2024-07-21T19:04:51.615173\",\n" +
                                "    \"isSuccess\": false,\n" +
                                "    \"code\": \"GLOBAL_400_2\",\n" +
                                "    \"message\": \"공백일 수 없습니다.\",\n" +
                                "    \"httpStatus\": 400\n" +
                                "}"),
                            schema = @Schema(implementation = SuccessResponse.class))),
            @ApiResponse(responseCode = "404", description = "링크 존재 X",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = "{\n" +
                                "    \"timestamp\": \"2024-07-21T18:33:43.578805\",\n" +
                                "    \"isSuccess\": false,\n" +
                                "    \"code\": \"LINK_404_1\",\n" +
                                "    \"message\": \"링크가 존재하지 않습니다.\",\n" +
                                "    \"httpStatus\": 404\n" +
                                "}"),
                            schema = @Schema(implementation = SuccessResponse.class))),
            @ApiResponse(responseCode = "404", description = "역할 존재 X",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = "{\n" +
                                "    \"timestamp\": \"2024-07-21T18:48:04.08734\",\n" +
                                "    \"isSuccess\": false,\n" +
                                "    \"code\": \"ROLE_404_1\",\n" +
                                "    \"message\": \"역할이 존재하지 않습니다.\",\n" +
                                "    \"httpStatus\": 404\n" +
                                "}"),
                            schema = @Schema(implementation = SuccessResponse.class))),
            @ApiResponse(responseCode = "409", description = "가족 내에서 닉네임 중복",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = "{\n" +
                                "    \"timestamp\": \"2024-07-21T18:56:38.381156\",\n" +
                                "    \"isSuccess\": false,\n" +
                                "    \"code\": \"USER_409_1\",\n" +
                                "    \"message\": \"가족 내에서 같은 닉네임을 사용할 수 없습니다.\",\n" +
                                "    \"httpStatus\": 409\n" +
                                "}"),
                            schema = @Schema(implementation = SuccessResponse.class)))
    })
    @PostMapping("/signUp")
    SuccessResponse<SignUpResponseDto> signUp(@RequestBody @Valid SignUpRequestDto dto);
}
