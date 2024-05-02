package com.openclassrooms.mddapi.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.openclassrooms.mddapi.dto.CommentDto;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.payload.request.CreateCommentRequest;
import com.openclassrooms.mddapi.services.CommentService;
import com.openclassrooms.mddapi.services.UserService;

import java.util.Collections;
import java.util.List;

import javax.validation.Valid;

/**
 * This class is a Spring Boot controller responsible for managing comments.
 */
@RestController
@RequestMapping("/api/comments")
public class CommentController {
    private final CommentService commentService;
    private final UserService userService;
    
    /**
     * Constructor for CommentController.
     * @param commentService The service for managing comments.
     * @param userService The service for managing users.
     */
    public CommentController(CommentService commentService, UserService userService) {
        this.commentService = commentService;
        this.userService = userService;
    }

    /**
     * GET method to retrieve comments by post ID.
     * @param postId The ID of the post to retrieve comments for.
     * @return ResponseEntity containing a list of CommentDto objects.
     */
    @GetMapping("/{postId}")
    public ResponseEntity<List<CommentDto>> getCommentsByPostId(@PathVariable("postId") Long postId) {
        List<CommentDto> comments = commentService.getCommentsByPostId(postId);
        return ResponseEntity.ok(comments);
    }

    /**
     * POST method to add a new comment.
     * @param commentRequest The request containing the details of the comment to add.
     * @return ResponseEntity indicating success or failure of the operation.
     */
    @PostMapping("/add")
    public ResponseEntity<?> addComment(@Valid @RequestBody CreateCommentRequest commentRequest) {
        try {
        	User loggedInUser = userService.getLoggedInUser();
        	if (loggedInUser.getId() != commentRequest.getAuthor()) {
        		User wrongUser = userService.findById(commentRequest.getAuthor()); 
        		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Vous n'avez pas les droits pour poster un commentaire en tant que " + wrongUser.getUsername());
			}
            commentService.addComment(commentRequest);
            return ResponseEntity.ok().body(Collections.singletonMap("message", "Commentaire ajouté avec succès !"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur est survenue lors de l'ajout du commentaire");
        }
    }
}
