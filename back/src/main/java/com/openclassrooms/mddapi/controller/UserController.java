package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.mapper.UserMapper;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.payload.request.ChangePasswordRequest;
import com.openclassrooms.mddapi.payload.request.UpdateProfileRequest;
import com.openclassrooms.mddapi.services.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

/**
 * This class is a Spring Boot controller responsible for managing users.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {
	private final UserMapper userMapper;
	private final UserService userService;
	private final PasswordEncoder passwordEncoder;

    /**
     * Constructor for UserController.
     * @param userService The service for managing users.
     * @param userMapper The mapper for converting between User and UserDto objects.
     * @param passwordEncoder The password encoder.
     */
	public UserController(UserService userService, UserMapper userMapper, PasswordEncoder passwordEncoder) {
		this.userMapper = userMapper;
		this.userService = userService;
		this.passwordEncoder = passwordEncoder;
	}

    /**
     * GET method to retrieve the current user.
     * @return ResponseEntity containing a UserDto object.
     */
	@GetMapping()
	public ResponseEntity<?> getCurrentUser() {
		try {
			User loggedInUser = userService.getLoggedInUser();

			return ResponseEntity.ok().body(this.userMapper.toDto(loggedInUser));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	// this could be used by admins in the future if needed
    /**
     * GET method to retrieve a user by their ID.
     * @param id The ID of the user to retrieve.
     * @return ResponseEntity containing a UserDto object.
     */
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") String id) {
		try {
			User loggedInUser = userService.getLoggedInUser();

			if (loggedInUser.isAdmin()) {
				User user = this.userService.findById(Long.valueOf(id));

				if (user == null) {
					return ResponseEntity.notFound().build();
				}

				return ResponseEntity.ok().body(this.userMapper.toDto(user));
			} else {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Vous n'avez pas les droits nécessaires.");
			}

		} catch (NumberFormatException e) {
			return ResponseEntity.badRequest().build();
		}
	}

    /**
     * PUT method to update the user's profile.
     * @param updatedProfile The request containing updated profile information.
     * @return ResponseEntity containing the updated UserDto object.
     */
	@PutMapping()
	public ResponseEntity<?> updateProfile(@RequestBody UpdateProfileRequest updatedProfile) {
		try {
			User loggedInUser = userService.getLoggedInUser();

			loggedInUser.setUsername(updatedProfile.getUsername());
			loggedInUser.setEmail(updatedProfile.getEmail());

			User user = userService.save(loggedInUser);
			return ResponseEntity.ok().body(this.userMapper.toDto(user));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Erreur lors de la mise à jour du profil utilisateur");
		}
	}

    /**
     * PUT method to update the user's password.
     * @param updatedPassword The request containing the updated password information.
     * @return ResponseEntity containing the updated UserDto object.
     */
	@PutMapping("/change-password")
	public ResponseEntity<?> updatePassword(@RequestBody ChangePasswordRequest updatedPassword) {
		User loggedInUser = userService.getLoggedInUser();

		if (!passwordEncoder.matches(updatedPassword.getOldPassword(), loggedInUser.getPassword())) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Ancien mot de passe incorrect");
		}

		try {
			loggedInUser.setPassword(passwordEncoder.encode(updatedPassword.getNewPassword()));

			User user = userService.save(loggedInUser);
			return ResponseEntity.ok().body(this.userMapper.toDto(user));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Erreur lors de la mise à jour du profil utilisateur");
		}
	}
}
