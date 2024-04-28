package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void delete(Long id) {
        this.userRepository.deleteById(id);
    }

    public User findById(Long id) {
        return this.userRepository.findById(id).orElse(null);
    }

    public String getUsernameById(Long id) {
    	User user = this.userRepository.findById(id).orElse(null);
        return user.getUsername();
    }
    
    public User findByUsername(String username) {
        return this.userRepository.findByUsername(username).orElse(null);
    }
    
    public User save(User user) {
        return userRepository.save(user);
    }
}
