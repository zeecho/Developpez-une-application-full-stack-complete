package com.openclassrooms.mddapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

/**
 * This class represents a Data Transfer Object (DTO) for users.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;

    @NonNull
    @Size(max = 50)
    @Email
    private String email;

    @NonNull
    @Size(max = 50)
    private String username;
    
    @JsonIgnore
    @Size(max = 120)
    private String password;
    
    @NonNull
    private boolean admin;

    private List<Long> topics;
    
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
