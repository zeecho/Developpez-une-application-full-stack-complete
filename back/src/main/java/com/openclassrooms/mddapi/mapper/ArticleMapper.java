package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.ArticleDto;
import com.openclassrooms.mddapi.models.Article;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.services.TopicService;
import com.openclassrooms.mddapi.services.UserService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", uses = {UserService.class, TopicService.class})
public abstract class ArticleMapper implements EntityMapper<ArticleDto, Article> {
    @Autowired
    protected UserService userService;
    
    @Autowired
    protected TopicService topicService;

    @Mappings({
            @Mapping(target = "author", source = "article.author.id"),
            @Mapping(target = "authorUsername", expression = "java(getAuthorUsername(article.getAuthor()))"),
            @Mapping(target = "topic", source = "article.topic.id")
    })
    public abstract ArticleDto toDto(Article article);

    public String getAuthorUsername(User author) {
        return userService.getUsernameById(author.getId());
    }

    @Mappings({
            @Mapping(target = "author", expression = "java(userService.findById(articleDto.getAuthor()))"),
            @Mapping(target = "topic", expression = "java(topicService.findById(articleDto.getTopic()))")
    })
    public abstract Article toEntity(ArticleDto articleDto);
}
