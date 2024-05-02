package com.openclassrooms.mddapi.payload.response;

import lombok.Getter;
import lombok.Setter;

/**
 * Represents a response containing a JWT token.
 */
@Getter
@Setter
public class JwtResponse {
  /** The JWT access token. */
  private String token;
  
  /** The type of token, usually "Bearer". */
  private String type = "Bearer";
  
  /** The ID of the user. */
  private Long id;
  
  /** The username of the user. */
  private String username;
  
  /** The email of the user. */
  private String email;

  /** Indicates if the user is an admin or not. */
  private Boolean admin;

  /**
   * Constructs a new JwtResponse with the provided details.
   * 
   * @param accessToken The JWT access token.
   * @param id          The ID of the user.
   * @param username    The username of the user.
   * @param email       The email of the user.
   * @param admin       Indicates if the user is an admin.
   */
  public JwtResponse(String accessToken, Long id, String username,String email, Boolean admin) {
    this.token = accessToken;
    this.id = id;
    this.email = email;
    this.username = username;
    this.admin = admin;
  }
}
