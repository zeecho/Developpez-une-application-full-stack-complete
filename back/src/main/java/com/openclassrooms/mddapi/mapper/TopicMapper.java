package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.TopicDto;
import com.openclassrooms.mddapi.models.Topic;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

/**
 * This class is a mapper responsible for converting between TopicDto and Topic entities.
 */
@Component
@Mapper(componentModel = "spring")
public interface TopicMapper extends EntityMapper<TopicDto, Topic> {
}
