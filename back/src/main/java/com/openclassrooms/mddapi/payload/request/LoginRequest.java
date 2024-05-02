package com.openclassrooms.mddapi.payload.request;

import javax.validation.constraints.NotBlank;

/**
 * This class represents a request payload for user login.
 */
public class LoginRequest {
	@NotBlank
	private String emailOrUsername;

	@NotBlank
	private String password;

    /**
     * Retrieves the email or username used for login.
     * 
     * @return The email or username used for login.
     */
	public String getEmailOrUsername() {
		return emailOrUsername;
	}

    /**
     * Sets the email or username used for login.
     * 
     * @param emailOrUsername The email or username used for login.
     */
	public void setEmailOrUsername(String emailOrUsername) {
		this.emailOrUsername = emailOrUsername;
	}

    /**
     * Retrieves the password used for login.
     * 
     * @return The password used for login.
     */
	public String getPassword() {
		return password;
	}

    /**
     * Sets the password used for login.
     * 
     * @param password The password used for login.
     */
	public void setPassword(String password) {
		this.password = password;
	}
}
