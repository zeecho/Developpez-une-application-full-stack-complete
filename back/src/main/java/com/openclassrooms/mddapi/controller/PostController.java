package com.openclassrooms.mddapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mddapi.dto.PostDto;
import com.openclassrooms.mddapi.mapper.PostMapper;
import com.openclassrooms.mddapi.models.Post;
import com.openclassrooms.mddapi.models.Topic;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.payload.request.CreatePostRequest;
import com.openclassrooms.mddapi.services.PostService;
import com.openclassrooms.mddapi.services.TopicService;
import com.openclassrooms.mddapi.services.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/posts")
public class PostController {
	private final PostService postService;
	private final PostMapper postMapper;
	private final TopicService topicService;
	private final UserService userService;

	public PostController(PostService postService, PostMapper postMapper, TopicService topicService,
			UserService userService) {
		this.postService = postService;
		this.postMapper = postMapper;
		this.topicService = topicService;
		this.userService = userService;
	}

	@GetMapping
	public List<PostDto> getAllPosts() {
		return postService.getAllPosts();
	}

	@GetMapping("/subscribed")
	public ResponseEntity<List<PostDto>> getPostsForSubscribedTopics() {
		try {
			User loggedInUser = userService.getLoggedInUser();
			List<Topic> subscribedTopics = topicService.findSubscribedTopicsByUserId(loggedInUser.getId());

			List<PostDto> postsForSubscribedTopics = new ArrayList<>();
			for (Topic topic : subscribedTopics) {
				List<PostDto> posts = postService.getPostsByTopicId(topic.getId());
				postsForSubscribedTopics.addAll(posts);
			}

			return ResponseEntity.ok(postsForSubscribedTopics);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@PostMapping("/create")
	public ResponseEntity<?> createPost(@RequestBody CreatePostRequest postRequest) {
		try {
			User loggedInUser = userService.getLoggedInUser();
			if (topicService.findById(postRequest.getTopic()) != null) {
				PostDto postDto = new PostDto();
				postDto.setAuthor(loggedInUser.getId());
				postDto.setContent(postRequest.getContent());
				postDto.setTitle(postRequest.getTitle());
				postDto.setTopic(postRequest.getTopic());
				Post post = this.postMapper.toEntity(postDto);
				postService.createPost(post);
				return ResponseEntity.status(HttpStatus.CREATED).build();
			} else {
				return ResponseEntity.badRequest().body("Le thème spécifié n'existe pas");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Une erreur est survenue lors de la création de l'article");
		}
	}

	@GetMapping("/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable("postId") Long postId) {
		try {
			Optional<Post> optionalPost = postService.getPostById(postId);
			if (optionalPost.isPresent()) {
				PostDto postDto = postMapper.toDto(optionalPost.get());
				return ResponseEntity.ok(postDto);
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

}
