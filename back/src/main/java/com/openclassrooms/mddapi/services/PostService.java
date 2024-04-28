package com.openclassrooms.mddapi.services;

import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.dto.PostDto;
import com.openclassrooms.mddapi.mapper.PostMapper;
import com.openclassrooms.mddapi.models.Post;
import com.openclassrooms.mddapi.repository.PostRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final PostMapper postMapper;

    public PostService(PostRepository postRepository, PostMapper postMapper) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
    }

    public List<PostDto> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream()
                .map(postMapper::toDto)
                .collect(Collectors.toList());
    }
    
    public List<PostDto> getPostsByTopicId(Long topicId) {
        List<Post> posts = postRepository.findByTopicId(topicId);
        return posts.stream()
                .map(postMapper::toDto)
                .collect(Collectors.toList());
    }

    public void createPost(Post post) {
        postRepository.save(post);
    }
    
    public Optional<Post> getPostById(Long postId) {
        return postRepository.findById(postId);
    }
    
    public Post findById(Long postId) {
    	return postRepository.findById(postId).orElse(null);
    }
}
