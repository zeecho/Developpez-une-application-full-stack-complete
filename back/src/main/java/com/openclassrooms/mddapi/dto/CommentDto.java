package com.openclassrooms.mddapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    private Long id;

    @NonNull
    @Size(max = 1000)
    private String content;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Long post;

    private Long author;
    
    private String authorUsername;
}
