package com.openclassrooms.mddapi.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.payload.request.LoginRequest;
import com.openclassrooms.mddapi.payload.request.SignupRequest;
import com.openclassrooms.mddapi.payload.response.JwtResponse;
import com.openclassrooms.mddapi.payload.response.MessageResponse;
import com.openclassrooms.mddapi.repository.UserRepository;
import com.openclassrooms.mddapi.security.jwt.JwtUtils;
import com.openclassrooms.mddapi.security.services.UserDetailsImpl;

/**
 * This class is a Spring Boot controller that handles user authentication and registration operations.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    /**
     * Constructor for AuthController class.
     * @param authenticationManager The Spring Security authentication manager.
     * @param passwordEncoder The Spring Security password encoder.
     * @param jwtUtils The JWT utility for generating and validating JWT tokens.
     * @param userRepository The user repository.
     */
    AuthController(AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder,
            JwtUtils jwtUtils,
            UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    /**
     * POST method for user authentication.
     * @param loginRequest The login request containing user credentials.
     * @return ResponseEntity containing the JWT token and details of the authenticated user.
     */
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmailOrUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        boolean isAdmin = false;
        User user = this.userRepository.findByEmail(userDetails.getUsername()).orElse(null);
        if (user != null) {
            isAdmin = user.isAdmin();
        }

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                isAdmin));
    }

    /**
     * POST method for registering new users.
     * @param signUpRequest The signup request containing user information to register.
     * @return ResponseEntity containing a success or error message.
     */
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already taken!"));
        }

        User user = new User(signUpRequest.getEmail(),
        		signUpRequest.getUsername(),
        		passwordEncoder.encode(signUpRequest.getPassword()),
        		false
        		);

        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}
