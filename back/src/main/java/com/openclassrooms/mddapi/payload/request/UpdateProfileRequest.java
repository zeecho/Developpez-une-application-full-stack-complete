package com.openclassrooms.mddapi.payload.request;

/**
 * This class represents a request payload for updating user profile.
 */
public class UpdateProfileRequest {
	private String username;

	private String email;

    /**
     * Retrieves the new username.
     * @return The new username.
     */
	public String getUsername() {
		return username;
	}

    /**
     * Retrieves the new email address.
     * @return The new email address.
     */
	public String getEmail() {
		return email;
	}
}
