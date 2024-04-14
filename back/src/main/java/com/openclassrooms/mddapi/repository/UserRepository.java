package com.openclassrooms.mddapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.mddapi.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    
//    User findByEmail(String email);
    
    Optional<User> findByEmail(String email);

    Boolean existsByEmail(String email); 
}

