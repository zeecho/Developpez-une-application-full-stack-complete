package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.models.Topic;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.repository.TopicRepository;
import com.openclassrooms.mddapi.repository.UserRepository;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicService {
    private final TopicRepository topicRepository;
    
    private final UserRepository userRepository;

    public TopicService(TopicRepository topicRepository, UserRepository userRepository) {
        this.topicRepository = topicRepository;
        this.userRepository = userRepository;
    }

    public Topic findById(Long id) {
        return this.topicRepository.findById(id).orElse(null);
    }

    public List<Topic> findAll() {
        return this.topicRepository.findAll();
    }
    
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
    
    public void delete(Long id) {
        this.topicRepository.deleteById(id);
    }
}
