package com.openclassrooms.mddapi.repository;

import com.openclassrooms.mddapi.models.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing Topic entities.
 */
@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {
}
