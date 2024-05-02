package com.openclassrooms.mddapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.mddapi.models.Post;

/**
 * Repository interface for managing Post entities.
 */
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    /**
     * Retrieves a list of posts based on the ID of the topic.
     * 
     * @param topicId The ID of the topic.
     * @return A list of posts belonging to the specified topic.
     */
    List<Post> findByTopicId(Long topicId);
}
