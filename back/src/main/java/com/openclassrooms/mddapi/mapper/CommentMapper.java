package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.CommentDto;
import com.openclassrooms.mddapi.models.Comment;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.services.ArticleService;
import com.openclassrooms.mddapi.services.UserService;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", uses = {UserService.class, ArticleService.class})
public abstract class CommentMapper implements EntityMapper<CommentDto, Comment> {
	@Autowired
	UserService userService;
	
	@Autowired
	ArticleService articleService;
	
    @Mappings({
            @Mapping(target = "author", source = "comment.author.id"),
            @Mapping(target = "authorUsername", expression = "java(getAuthorUsername(comment.getAuthor()))"),
            @Mapping(target = "article", source = "comment.article.id")
    })
    public abstract CommentDto toDto(Comment comment);

    public String getAuthorUsername(User author) {
        return userService.getUsernameById(author.getId());
    }

    @Mappings({
            @Mapping(target = "author", expression = "java(userService.findById(commentDto.getAuthor()))"),
            @Mapping(target = "article", expression = "java(articleService.findById(commentDto.getArticle()))")
    })
    public abstract Comment toEntity(CommentDto commentDto);
}
