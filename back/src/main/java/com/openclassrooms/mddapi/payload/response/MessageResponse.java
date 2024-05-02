package com.openclassrooms.mddapi.payload.response;

/**
 * Represents a simple message response.
 */
public class MessageResponse {
  private String message;

  /**
   * Constructs a new MessageResponse with the provided message.
   * 
   * @param message The message to be encapsulated in the response.
   */
  public MessageResponse(String message) {
    this.message = message;
  }

  /**
   * Retrieves the message.
   * 
   * @return The message.
   */
  public String getMessage() {
    return message;
  }

  /**
   * Sets the message.
   * 
   * @param message The message to be set.
   */
  public void setMessage(String message) {
    this.message = message;
  }
}
