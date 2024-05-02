package com.openclassrooms.mddapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.mddapi.models.Comment;

import java.util.List;

/**
 * Repository interface for managing Comment entities.
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    /**
     * Retrieves a list of comments based on the ID of the post.
     * 
     * @param postId The ID of the post.
     * @return A list of comments belonging to the specified post.
     */
    List<Comment> findByPostId(Long author);
}
