package com.openclassrooms.mddapi.payload.request;

import javax.validation.constraints.NotBlank;

/**
 * This class represents a request payload for creating a post.
 */
public class CreatePostRequest {
	@NotBlank
	private String title;

	@NotBlank
	private String content;
	
	private Long topic;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getTopic() {
		return topic;
	}

	public void setTopic(Long topic) {
		this.topic = topic;
	}
}
