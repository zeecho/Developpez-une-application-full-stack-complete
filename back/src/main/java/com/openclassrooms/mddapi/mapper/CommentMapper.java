package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.CommentDto;
import com.openclassrooms.mddapi.models.Comment;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.services.PostService;
import com.openclassrooms.mddapi.services.UserService;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", uses = {UserService.class, PostService.class})
public abstract class CommentMapper implements EntityMapper<CommentDto, Comment> {
	@Autowired
	UserService userService;
	
	@Autowired
	PostService postService;
	
    @Mappings({
            @Mapping(target = "author", source = "comment.author.id"),
            @Mapping(target = "authorUsername", expression = "java(getAuthorUsername(comment.getAuthor()))"),
            @Mapping(target = "post", source = "comment.post.id")
    })
    public abstract CommentDto toDto(Comment comment);

    public String getAuthorUsername(User author) {
        return userService.getUsernameById(author.getId());
    }

    @Mappings({
            @Mapping(target = "author", expression = "java(userService.findById(commentDto.getAuthor()))"),
            @Mapping(target = "post", expression = "java(postService.findById(commentDto.getPost()))")
    })
    public abstract Comment toEntity(CommentDto commentDto);
}
