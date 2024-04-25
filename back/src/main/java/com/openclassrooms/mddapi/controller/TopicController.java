package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.TopicDto;
import com.openclassrooms.mddapi.mapper.TopicMapper;
import com.openclassrooms.mddapi.models.Topic;
import com.openclassrooms.mddapi.services.TopicService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/topics")
public class TopicController {
    private final TopicMapper topicMapper;
    private final TopicService topicService;

    public TopicController(TopicService topicService, TopicMapper topicMapper) {
        this.topicService = topicService;
        this.topicMapper = topicMapper;
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
    
    @PostMapping("{id}/subscribe/{userId}")
    public ResponseEntity<?> participate(@PathVariable("id") String id, @PathVariable("userId") String userId) throws Exception {
        try {
        	try {
        		this.topicService.subscribe(Long.parseLong(id), Long.parseLong(userId));
        	} catch (Exception e){
        		throw e;
        	}

            return ResponseEntity.ok().build();
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        try {
            Topic topic = this.topicService.findById(id);

            if (topic == null) {
                return ResponseEntity.notFound().build();
            }

            this.topicService.delete(id);
            return ResponseEntity.ok().build();
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
