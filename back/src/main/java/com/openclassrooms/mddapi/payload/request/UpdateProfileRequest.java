package com.openclassrooms.mddapi.payload.request;

/**
 * This class represents a request payload for updating user profile.
 */
public class UpdateProfileRequest {
	private String username;

	private String email;

	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}
}
