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

/**
 * This class is a mapper responsible for converting between CommentDto and Comment entities.
 */
@Component
@Mapper(componentModel = "spring", uses = {UserService.class, PostService.class})
public abstract class CommentMapper implements EntityMapper<CommentDto, Comment> {
	@Autowired
	UserService userService;
	
	@Autowired
	PostService postService;
	
    /**
     * Converts a Comment entity to a CommentDto.
     * @param comment The Comment entity to convert.
     * @return The resulting CommentDto.
     */
    @Mappings({
            @Mapping(target = "author", source = "comment.author.id"),
            @Mapping(target = "authorUsername", expression = "java(getAuthorUsername(comment.getAuthor()))"),
            @Mapping(target = "post", source = "comment.post.id")
    })
    public abstract CommentDto toDto(Comment comment);

    /**
     * Retrieves the username of the author of the comment.
     * @param author The author of the comment.
     * @return The username of the author.
     */
    public String getAuthorUsername(User author) {
        return userService.getUsernameById(author.getId());
    }

    /**
     * Converts a CommentDto to a Comment entity.
     * @param commentDto The CommentDto to convert.
     * @return The resulting Comment entity.
     */
    @Mappings({
            @Mapping(target = "author", expression = "java(userService.findById(commentDto.getAuthor()))"),
            @Mapping(target = "post", expression = "java(postService.findById(commentDto.getPost()))")
    })
    public abstract Comment toEntity(CommentDto commentDto);
}
