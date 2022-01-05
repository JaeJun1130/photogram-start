package com.cos.photogramstart.web.dto.comment;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class CommentDto {
    @NotBlank //빈값이거나 널 체크, 공백체
    private String content;
    @NotNull //빈값이거나 널 체크
    private Integer imageId;
}
