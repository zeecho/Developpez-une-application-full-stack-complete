package com.openclassrooms.mddapi.payload.request;

import javax.validation.constraints.NotBlank;

/**
 * This class represents a request payload for changing the password.
 */
public class ChangePasswordRequest {
	@NotBlank
	private String oldPassword;

	@NotBlank
	private String newPassword;

	/**
	 * Retrieves the old password from the request.
	 * 
	 * @return The old password.
	 */
	public String getOldPassword() {
		return oldPassword;
	}
	/**
	 * Retrieves the new password from the request.
	 * 
	 * @return The new password.
	 */
	public String getNewPassword() {
		return newPassword;
	}
}
