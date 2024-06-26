package com.openclassrooms.mddapi.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * This class represents a Data Transfer Object (DTO) for posts.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
    private Long id;

    @NonNull
    @Size(max = 255)
    private String title;

    @NonNull
    private String content;
   
    private Long author;
    
    private String authorUsername;
    
    private Long topic;
    
    private LocalDateTime createdAt;
    
    private LocalDateTime updatedAt;
}
