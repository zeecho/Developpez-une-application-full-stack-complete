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

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {
	private final UserMapper userMapper;
	private final UserService userService;
	private final PasswordEncoder passwordEncoder;

	public UserController(UserService userService, UserMapper userMapper, PasswordEncoder passwordEncoder) {
		this.userMapper = userMapper;
		this.userService = userService;
		this.passwordEncoder = passwordEncoder;
	}

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

//    @DeleteMapping("{id}")
//    public ResponseEntity<?> save(@PathVariable("id") String id) {
//        try {
//            User user = this.userService.findById(Long.valueOf(id));
//
//            if (user == null) {
//                return ResponseEntity.notFound().build();
//            }
//
//            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//            if(!Objects.equals(userDetails.getUsername(), user.getEmail())) {
//                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//            }
//
//            this.userService.delete(Long.parseLong(id));
//            return ResponseEntity.ok().build();
//        } catch (NumberFormatException e) {
//            return ResponseEntity.badRequest().build();
//        }
//    }
}
