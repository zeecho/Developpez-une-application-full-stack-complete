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

	public String getOldPassword() {
		return oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}
}
