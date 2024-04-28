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

@Component
@Mapper(componentModel = "spring", uses = {UserService.class, TopicService.class})
public abstract class PostMapper implements EntityMapper<PostDto, Post> {
    @Autowired
    protected UserService userService;
    
    @Autowired
    protected TopicService topicService;

    @Mappings({
            @Mapping(target = "author", source = "post.author.id"),
            @Mapping(target = "authorUsername", expression = "java(getAuthorUsername(post.getAuthor()))"),
            @Mapping(target = "topic", source = "post.topic.id")
    })
    public abstract PostDto toDto(Post post);

    public String getAuthorUsername(User author) {
        return userService.getUsernameById(author.getId());
    }

    @Mappings({
            @Mapping(target = "author", expression = "java(userService.findById(postDto.getAuthor()))"),
            @Mapping(target = "topic", expression = "java(topicService.findById(postDto.getTopic()))")
    })
    public abstract Post toEntity(PostDto postDto);
}
