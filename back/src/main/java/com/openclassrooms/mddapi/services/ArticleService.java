package com.openclassrooms.mddapi.services;

import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.dto.ArticleDto;
import com.openclassrooms.mddapi.mapper.ArticleMapper;
import com.openclassrooms.mddapi.models.Article;
import com.openclassrooms.mddapi.repository.ArticleRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;

    public ArticleService(ArticleRepository articleRepository, ArticleMapper articleMapper) {
        this.articleRepository = articleRepository;
        this.articleMapper = articleMapper;
    }

    public List<ArticleDto> getAllArticles() {
        List<Article> articles = articleRepository.findAll();
        return articles.stream()
                .map(articleMapper::toDto)
                .collect(Collectors.toList());
    }
    
    public List<ArticleDto> getArticlesByTopicId(Long topicId) {
        List<Article> articles = articleRepository.findByTopicId(topicId);
        return articles.stream()
                .map(articleMapper::toDto)
                .collect(Collectors.toList());
    }

    public void createArticle(Article article) {
        articleRepository.save(article);
    }
    
    public Optional<Article> getArticleById(Long articleId) {
        return articleRepository.findById(articleId);
    }
    
    public Article findById(Long articleId) {
    	return articleRepository.findById(articleId).orElse(null);
    }
}
