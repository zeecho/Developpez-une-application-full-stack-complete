package com.openclassrooms.mddapi.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.openclassrooms.mddapi.dto.CommentDto;
import com.openclassrooms.mddapi.payload.request.CreateCommentRequest;
import com.openclassrooms.mddapi.services.CommentService;

import java.util.Collections;
import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    private final CommentService commentService;
    
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/{articleId}")
    public ResponseEntity<List<CommentDto>> getCommentsByArticleId(@PathVariable("articleId") Long articleId) {
        List<CommentDto> comments = commentService.getCommentsByArticleId(articleId);
        return ResponseEntity.ok(comments);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addComment(@Valid @RequestBody CreateCommentRequest commentRequest) {
        try {
            commentService.addComment(commentRequest);
            return ResponseEntity.ok().body(Collections.singletonMap("message", "Commentaire ajouté avec succès !"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur est survenue lors de l'ajout du commentaire");
        }
    }
}
