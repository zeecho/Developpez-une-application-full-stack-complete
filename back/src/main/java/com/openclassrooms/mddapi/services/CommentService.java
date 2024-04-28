package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.dto.CommentDto;
import com.openclassrooms.mddapi.mapper.CommentMapper;
import com.openclassrooms.mddapi.models.Article;
import com.openclassrooms.mddapi.models.Comment;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.payload.request.CreateCommentRequest;
import com.openclassrooms.mddapi.repository.ArticleRepository;
import com.openclassrooms.mddapi.repository.CommentRepository;
import com.openclassrooms.mddapi.repository.UserRepository;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    
    public CommentService(CommentRepository commentRepository, CommentMapper commentMapper, ArticleRepository articleRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
    }

    public List<CommentDto> getCommentsByArticleId(Long articleId) {
        return commentMapper.toDto(commentRepository.findByArticleId(articleId));
    }
    
    public void addComment(CreateCommentRequest commentRequest) throws Exception {
        Article article = articleRepository.findById(commentRequest.getArticle()).orElse(null);
        User user = userRepository.findById(commentRequest.getAuthor()).orElse(null);

        if (article == null || user == null) {
            throw new NotFoundException();
        }

        Comment comment = new Comment()
                .setContent(commentRequest.getContent())
                .setArticle(article)
                .setAuthor(user);

        commentRepository.save(comment);
    }
}
