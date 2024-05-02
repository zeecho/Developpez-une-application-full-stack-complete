package com.openclassrooms.mddapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.mddapi.models.User;

/**
 * Repository interface for managing User entities.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Retrieves an optional user by their username.
     * 
     * @param username The username of the user to retrieve.
     * @return An optional containing the user with the specified username, if found.
     */
    Optional<User> findByUsername(String username);
    
    /**
     * Retrieves an optional user by their email address.
     * 
     * @param email The email address of the user to retrieve.
     * @return An optional containing the user with the specified email address, if found.
     */
    Optional<User> findByEmail(String email);

    /**
     * Checks if a user exists with the specified email address.
     * 
     * @param email The email address to check.
     * @return True if a user exists with the specified email address, otherwise false.
     */
    Boolean existsByEmail(String email); 
    
    /**
     * Retrieves an optional user by their username or email address.
     * 
     * @param username The username to search for.
     * @param email The email address to search for.
     * @return An optional containing the user found by username or email address, if exists.
     */
    Optional<User> findByUsernameOrEmail(String username, String email);
}

