package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.repository.UserRepository;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * This class provides services related to users.
 */
@Service
public class UserService {
    private final UserRepository userRepository;

    /**
     * Constructs a UserService with the specified UserRepository.
     * @param userRepository The repository for managing user entities.
     */
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Deletes a user by ID.
     * @param id The ID of the user to delete.
     */
    public void delete(Long id) {
        this.userRepository.deleteById(id);
    }

    /**
     * Finds a user by ID.
     * @param id The ID of the user.
     * @return The found user, or null if not found.
     */
    public User findById(Long id) {
        return this.userRepository.findById(id).orElse(null);
    }

    /**
     * Retrieves the username of a user by ID.
     * @param id The ID of the user.
     * @return The username of the user.
     */
    public String getUsernameById(Long id) {
    	User user = this.userRepository.findById(id).orElse(null);
        return user.getUsername();
    }
    
    /**
     * Finds a user by username.
     * @param username The username of the user.
     * @return The found user, or null if not found.
     */
    public User findByUsername(String username) {
        return this.userRepository.findByUsername(username).orElse(null);
    }
    
    /**
     * Saves a user.
     * @param user The user to save.
     * @return The saved user.
     */
    public User save(User user) {
        return userRepository.save(user);
    }
    
    /**
     * Retrieves the currently logged-in user.
     * @return The logged-in user.
     */
    public User getLoggedInUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return findByUsername(auth.getName());
	}
}
