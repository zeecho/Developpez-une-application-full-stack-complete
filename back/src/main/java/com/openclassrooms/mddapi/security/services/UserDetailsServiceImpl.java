package com.openclassrooms.mddapi.security.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.repository.UserRepository;

/**
 * Service class for loading user-specific data.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  UserRepository userRepository;

  UserDetailsServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }
  
  /**
   * Loads user-specific data by username or email.
   * @param usernameOrEmail The username or email.
   * @return UserDetails containing user-specific data.
   * @throws UsernameNotFoundException If the user is not found.
   */
  @Override
  @Transactional
  public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
    User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username or email: " + usernameOrEmail));

    return UserDetailsImpl
        .builder()
        .id(user.getId())
        .username(user.getUsername())
        .email(user.getEmail())
        .password(user.getPassword())
        .build();
  }
}
