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

    /**
     * Retrieves the title of the post.
     * 
     * @return The title of the post.
     */
	public String getTitle() {
		return title;
	}

    /**
     * Sets the title of the post.
     * 
     * @param title The title of the post.
     */
	public void setTitle(String title) {
		this.title = title;
	}

    /**
     * Retrieves the content of the post.
     * 
     * @return The content of the post.
     */
	public String getContent() {
		return content;
	}

    /**
     * Sets the content of the post.
     * 
     * @param content The content of the post.
     */
	public void setContent(String content) {
		this.content = content;
	}

    /**
     * Retrieves the topic ID of the post.
     * 
     * @return The topic ID of the post.
     */
	public Long getTopic() {
		return topic;
	}

    /**
     * Sets the topic ID of the post.
     * 
     * @param topic The topic ID of the post.
     */
	public void setTopic(Long topic) {
		this.topic = topic;
	}
}
