package com.openclassrooms.mddapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mddapi.dto.ArticleDto;
import com.openclassrooms.mddapi.mapper.ArticleMapper;
import com.openclassrooms.mddapi.models.Article;
import com.openclassrooms.mddapi.models.Topic;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.payload.request.CreateArticleRequest;
import com.openclassrooms.mddapi.repository.UserRepository;
import com.openclassrooms.mddapi.services.ArticleService;
import com.openclassrooms.mddapi.services.TopicService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/articles")
public class ArticleController {
    private final ArticleService articleService;
    private final ArticleMapper articleMapper;
    private final UserRepository userRepository;
    private final TopicService topicService;
    
    public ArticleController(ArticleService articleService, ArticleMapper articleMapper, UserRepository userRepository, TopicService topicService) {
        this.articleService = articleService;
        this.articleMapper = articleMapper;
        this.userRepository = userRepository;
        this.topicService = topicService;
    }

    @GetMapping
    public List<ArticleDto> getAllArticles() {
        return articleService.getAllArticles();
    }
    
    @GetMapping("/subscribed/{userId}")
    public ResponseEntity<List<ArticleDto>> getArticlesForSubscribedTopics(@PathVariable("userId") Long userId) {
        try {
            List<Topic> subscribedTopics = topicService.findSubscribedTopicsByUserId(userId);

            List<ArticleDto> articlesForSubscribedTopics = new ArrayList<>();
            for (Topic topic : subscribedTopics) {
                List<ArticleDto> articles = articleService.getArticlesByTopicId(topic.getId());
                articlesForSubscribedTopics.addAll(articles);
            }

            return ResponseEntity.ok(articlesForSubscribedTopics);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    
    @PostMapping("/create/{userId}")
    public ResponseEntity<?> createArticle(@PathVariable("userId") Long userId, @RequestBody CreateArticleRequest articleRequest) {
        try {
            Optional<User> optionalUser = userRepository.findById(userId);
            if (optionalUser.isPresent()) {
                if (topicService.findById(articleRequest.getTopic()) != null) {
                	ArticleDto articleDto = new ArticleDto();
                	articleDto.setAuthor(userId);
                	articleDto.setContent(articleRequest.getContent());
                	articleDto.setTitle(articleRequest.getTitle());
                	articleDto.setTopic(articleRequest.getTopic());
                	Article article = this.articleMapper.toEntity(articleDto);
                    articleService.createArticle(article);
                    return ResponseEntity.status(HttpStatus.CREATED).build();
                } else {
                    return ResponseEntity.badRequest().body("Le sujet spécifié n'existe pas");
                }
            } else {
                return ResponseEntity.badRequest().body("L'utilisateur spécifié n'existe pas");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur est survenue lors de la création de l'article");
        }
    }
    
    @GetMapping("/{articleId}")
    public ResponseEntity<ArticleDto> getArticleById(@PathVariable("articleId") Long articleId) {
        try {
            Optional<Article> optionalArticle = articleService.getArticleById(articleId);
            if (optionalArticle.isPresent()) {
                ArticleDto articleDto = articleMapper.toDto(optionalArticle.get());
                return ResponseEntity.ok(articleDto);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
