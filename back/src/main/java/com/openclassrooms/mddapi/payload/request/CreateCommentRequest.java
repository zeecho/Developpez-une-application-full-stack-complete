package com.openclassrooms.mddapi.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * This class represents a request payload for creating a comment.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCommentRequest {
    @NotBlank
    private String content;

    @NotNull
    private Long post;

    @NotNull
    private Long author;
}
