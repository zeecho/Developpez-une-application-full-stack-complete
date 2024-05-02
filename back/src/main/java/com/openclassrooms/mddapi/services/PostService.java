package com.openclassrooms.mddapi.services;

import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.dto.PostDto;
import com.openclassrooms.mddapi.mapper.PostMapper;
import com.openclassrooms.mddapi.models.Post;
import com.openclassrooms.mddapi.repository.PostRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * This class provides services related to posts.
 */
@Service
public class PostService {
    private final PostRepository postRepository;
    private final PostMapper postMapper;

    /**
     * Constructs a PostService with the specified PostRepository and PostMapper.
     * @param postRepository The repository for managing post entities.
     * @param postMapper The mapper for converting between Post and PostDto.
     */
    public PostService(PostRepository postRepository, PostMapper postMapper) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
    }

    /**
     * Retrieves all posts.
     * @return A list of post DTOs.
     */
    public List<PostDto> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream()
                .map(postMapper::toDto)
                .collect(Collectors.toList());
    }
    
    /**
     * Retrieves posts by topic ID.
     * @param topicId The ID of the topic.
     * @return A list of post DTOs.
     */
    public List<PostDto> getPostsByTopicId(Long topicId) {
        List<Post> posts = postRepository.findByTopicId(topicId);
        return posts.stream()
                .map(postMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Creates a post.
     * @param post The post to create.
     */
    public void createPost(Post post) {
        postRepository.save(post);
    }
    
    /**
     * Retrieves a post by ID.
     * @param postId The ID of the post.
     * @return An optional containing the post, or empty if not found.
     */
    public Optional<Post> getPostById(Long postId) {
        return postRepository.findById(postId);
    }
    
    /**
     * Finds a post by ID.
     * @param postId The ID of the post.
     * @return The found post, or null if not found.
     */
    public Post findById(Long postId) {
    	return postRepository.findById(postId).orElse(null);
    }
}
