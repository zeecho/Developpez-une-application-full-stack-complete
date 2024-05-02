package com.openclassrooms.mddapi.payload.request;

import javax.validation.constraints.*;

import lombok.Data;

/**
 * This class represents a request payload for user signup.
 */
@Data
public class SignupRequest {
  @NotBlank
  @Size(max = 50)
  @Email
  private String email;

  @NotBlank
  @Size(min = 3, max = 30)
  private String username;

  @NotBlank
  @Size(min = 6, max = 40)
  private String password;
}
