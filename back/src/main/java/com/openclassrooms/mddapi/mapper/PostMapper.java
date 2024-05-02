package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.PostDto;
import com.openclassrooms.mddapi.models.Post;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.services.TopicService;
import com.openclassrooms.mddapi.services.UserService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * This class is a mapper responsible for converting between PostDto and Post entities.
 */
@Component
@Mapper(componentModel = "spring", uses = {UserService.class, TopicService.class})
public abstract class PostMapper implements EntityMapper<PostDto, Post> {
    /**
     * Service for managing user-related operations.
     */
	@Autowired
    protected UserService userService;
    
    /**
     * Service for managing topic-related operations.
     */
    @Autowired
    protected TopicService topicService;

    /**
     * Converts a Post entity to a PostDto.
     * @param post The Post entity to convert.
     * @return The resulting PostDto.
     */
    @Mappings({
            @Mapping(target = "author", source = "post.author.id"),
            @Mapping(target = "authorUsername", expression = "java(getAuthorUsername(post.getAuthor()))"),
            @Mapping(target = "topic", source = "post.topic.id")
    })
    public abstract PostDto toDto(Post post);

    /**
     * Retrieves the username of the author of the post.
     * @param author The author of the post.
     * @return The username of the author.
     */
    public String getAuthorUsername(User author) {
        return userService.getUsernameById(author.getId());
    }

    /**
     * Converts a PostDto to a Post entity.
     * @param postDto The PostDto to convert.
     * @return The resulting Post entity.
     */
    @Mappings({
            @Mapping(target = "author", expression = "java(userService.findById(postDto.getAuthor()))"),
            @Mapping(target = "topic", expression = "java(topicService.findById(postDto.getTopic()))")
    })
    public abstract Post toEntity(PostDto postDto);
}
