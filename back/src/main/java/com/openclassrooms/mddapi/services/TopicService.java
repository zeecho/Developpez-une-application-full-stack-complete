package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.models.Topic;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.repository.TopicRepository;
import com.openclassrooms.mddapi.repository.UserRepository;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This class provides services related to topics.
 */
@Service
public class TopicService {
    private final TopicRepository topicRepository;
    
    private final UserRepository userRepository;

    /**
     * Constructs a TopicService with the specified TopicRepository and UserRepository.
     * @param topicRepository The repository for managing topic entities.
     * @param userRepository The repository for managing user entities.
     */
    public TopicService(TopicRepository topicRepository, UserRepository userRepository) {
        this.topicRepository = topicRepository;
        this.userRepository = userRepository;
    }

    /**
     * Finds a topic by ID.
     * @param id The ID of the topic.
     * @return The found topic, or null if not found.
     */
    public Topic findById(Long id) {
        return this.topicRepository.findById(id).orElse(null);
    }

    /**
     * Finds all topics.
     * @return A list of topics.
     */
    public List<Topic> findAll() {
        return this.topicRepository.findAll();
    }
    
    /**
     * Finds subscribed topics by user ID.
     * @param userId The ID of the user.
     * @return A list of subscribed topics.
     * @throws NotFoundException if the user is not found.
     */
    public List<Topic> findSubscribedTopicsByUserId(Long userId) throws Exception {
    	User user = this.userRepository.findById(userId).orElse(null);

        if (user == null) {
            throw new NotFoundException();
        }
        
        return user.getTopics();
    }
    
    /**
     * Subscribes a user to a topic.
     * @param id The ID of the topic to subscribe to.
     * @param userId The ID of the user subscribing.
     * @throws NotFoundException if the topic or user is not found.
     * @throws Exception if the user is already subscribed to the topic.
     */
    public void subscribe(Long id, Long userId) throws Exception {
        Topic topic = this.topicRepository.findById(id).orElse(null);
        User user = this.userRepository.findById(userId).orElse(null);
        
        if (topic == null || user == null) {
            throw new NotFoundException();
        }

        boolean alreadyParticipate = user.getTopics().stream().anyMatch(o -> o.getId().equals(id));
        if(alreadyParticipate) {
            throw new Exception();
        }

        user.getTopics().add(topic);

        this.topicRepository.save(topic);
    }
    
    /**
     * Unsubscribes a user from a topic.
     * @param id The ID of the topic to unsubscribe from.
     * @param userId The ID of the user unsubscribing.
     * @throws NotFoundException if the topic or user is not found.
     * @throws Exception if the user is not subscribed to the topic.
     */
    public void unsubscribe(Long id, Long userId) throws Exception {
        Topic topic = this.topicRepository.findById(id).orElse(null);
        User user = this.userRepository.findById(userId).orElse(null);
        
        if (topic == null || user == null) {
            throw new NotFoundException();
        }

        boolean alreadyParticipate = user.getTopics().stream().anyMatch(o -> o.getId().equals(id));
        if(!alreadyParticipate) {
            throw new Exception();
        }

        user.getTopics().remove(topic);

        this.topicRepository.save(topic);
    }
    
    /**
     * Deletes a topic by ID.
     * @param id The ID of the topic to delete.
     */
    public void delete(Long id) {
        this.topicRepository.deleteById(id);
    }
}
