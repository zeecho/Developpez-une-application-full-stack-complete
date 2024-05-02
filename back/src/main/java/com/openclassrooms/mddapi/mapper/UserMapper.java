package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.models.Topic;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.services.TopicService;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * This class is a mapper responsible for converting between UserDto and User entities.
 */
@Component
@Mapper(componentModel = "spring", uses = {TopicService.class}, imports = {Arrays.class, Collectors.class, Topic.class, User.class, Collections.class, Optional.class})
public abstract class UserMapper implements EntityMapper<UserDto, User> {
	@Autowired
	TopicService topicService;
	
    /**
     * Converts a UserDto to a User entity.
     * @param userDto The UserDto to convert.
     * @return The resulting User entity.
     */
    @Mappings({
            @Mapping(target = "topics", expression = "java(Optional.ofNullable(userDto.getTopics()).orElseGet(Collections::emptyList).stream().map(topic_id -> { Topic topic = this.topicService.findById(topic_id); if (topic != null) { return topic; } return null; }).collect(Collectors.toList()))"),
    })
    public abstract User toEntity(UserDto userDto);
    
    /**
     * Converts a User entity to a UserDto.
     * @param user The User entity to convert.
     * @return The resulting UserDto.
     */
    @Mappings({
            @Mapping(target = "topics", expression = "java(Optional.ofNullable(user.getTopics()).orElseGet(Collections::emptyList).stream().map(t -> t.getId()).collect(Collectors.toList()))"),
    })
    public abstract UserDto toDto(User user);
}
