package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.TopicDto;
import com.openclassrooms.mddapi.mapper.TopicMapper;
import com.openclassrooms.mddapi.models.Topic;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.services.TopicService;
import com.openclassrooms.mddapi.services.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/topics")
public class TopicController {
    private final TopicMapper topicMapper;
    private final TopicService topicService;
	private final UserService userService;

    public TopicController(TopicService topicService, TopicMapper topicMapper, UserService userService) {
        this.topicService = topicService;
        this.topicMapper = topicMapper;
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        try {
            Topic topic = this.topicService.findById(Long.valueOf(id));

            if (topic == null) {
                return ResponseEntity.notFound().build();
            }

            TopicDto topicDto = this.topicMapper.toDto(topic);
            return ResponseEntity.ok().body(topicDto);
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<TopicDto>> findAll() {

        List<Topic> topics = this.topicService.findAll();

        return ResponseEntity.ok().body(this.topicMapper.toDto(topics));
    }

    @GetMapping("/subscribed")
    public ResponseEntity<List<TopicDto>> findSubscribedTopicsByUserId() throws Exception {
    	try {
			User loggedInUser = userService.getLoggedInUser();
    		List<Topic> topics = this.topicService.findSubscribedTopicsByUserId(loggedInUser.getId());
    		return ResponseEntity.ok().body(this.topicMapper.toDto(topics));
    	} catch (Exception e) {
            return ResponseEntity.badRequest().build();
		}
    }
    
    @PostMapping("{id}/subscribe")
    public ResponseEntity<?> subscribe(@PathVariable("id") String id) throws Exception {
        try {			
        	User loggedInUser = userService.getLoggedInUser();
    		this.topicService.subscribe(Long.parseLong(id), loggedInUser.getId());

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("{id}/unsubscribe")
    public ResponseEntity<?> unsubscribe(@PathVariable("id") String id) throws Exception {
        try {
        	User loggedInUser = userService.getLoggedInUser();
    		this.topicService.unsubscribe(Long.parseLong(id), loggedInUser.getId());

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
//        try {
//            Topic topic = this.topicService.findById(id);
//
//            if (topic == null) {
//                return ResponseEntity.notFound().build();
//            }
//
//            this.topicService.delete(id);
//            return ResponseEntity.ok().build();
//        } catch (NumberFormatException e) {
//            return ResponseEntity.badRequest().build();
//        }
//    }
}
