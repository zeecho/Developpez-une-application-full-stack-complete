package com.openclassrooms.mddapi.payload.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtResponse {
  private String token;
  private String type = "Bearer";
  private Long id;
  private String username;
  private String email;

  private Boolean admin;

  public JwtResponse(String accessToken, Long id, String username,String email, Boolean admin) {
    this.token = accessToken;
    this.id = id;
    this.email = email;
    this.username = username;
    this.admin = admin;
  }
}
