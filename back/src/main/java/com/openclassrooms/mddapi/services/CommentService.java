package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.dto.CommentDto;
import com.openclassrooms.mddapi.mapper.CommentMapper;
import com.openclassrooms.mddapi.models.Post;
import com.openclassrooms.mddapi.models.Comment;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.payload.request.CreateCommentRequest;
import com.openclassrooms.mddapi.repository.PostRepository;
import com.openclassrooms.mddapi.repository.CommentRepository;
import com.openclassrooms.mddapi.repository.UserRepository;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This class provides services related to comments.
 */
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    
    public CommentService(CommentRepository commentRepository, CommentMapper commentMapper, PostRepository postRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    /**
     * Retrieves comments by post ID.
     * @param postId The ID of the post.
     * @return A list of comment DTOs.
     */
    public List<CommentDto> getCommentsByPostId(Long postId) {
        return commentMapper.toDto(commentRepository.findByPostId(postId));
    }
    
    /**
     * Adds a comment.
     * @param commentRequest The request payload for creating a comment.
     * @throws NotFoundException if the post or user is not found.
     */
    public void addComment(CreateCommentRequest commentRequest) throws Exception {
        Post post = postRepository.findById(commentRequest.getPost()).orElse(null);
        User user = userRepository.findById(commentRequest.getAuthor()).orElse(null);

        if (post == null || user == null) {
            throw new NotFoundException();
        }

        Comment comment = new Comment()
                .setContent(commentRequest.getContent())
                .setPost(post)
                .setAuthor(user);

        commentRepository.save(comment);
    }
}
