package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.mapper.UserMapper;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserMapper userMapper;
    private final UserService userService;


    public UserController(UserService userService,
                             UserMapper userMapper) {
        this.userMapper = userMapper;
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") String id) {
        try {
            User user = this.userService.findById(Long.valueOf(id));

            if (user == null) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok().body(this.userMapper.toDto(user));
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PutMapping("/{userId}")
    public ResponseEntity<?> updateProfile(@PathVariable("userId") Long userId, @RequestBody UserDto updatedProfile) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User loggedInUser = userService.findByUsername(auth.getName());
        
        if (!Objects.equals(userId, loggedInUser.getId())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        
        try {
            User userToUpdate = userService.findById(userId);
            userToUpdate.setUsername(updatedProfile.getUsername());
            userToUpdate.setEmail(updatedProfile.getEmail());
            
            User user = userService.save(userToUpdate);
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
