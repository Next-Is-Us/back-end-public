package com.nextisus.project.mom.roomcomment.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCommentRequestDto {

    @NotNull(message = "roomPostId를 입력해주세요.")
    private Long roomPostId;
    @NotNull(message = "댓글의 내용을 입력해주세요")
    private String commentContent;
}
