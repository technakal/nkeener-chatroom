package edu.udacity.java.nano.chat;

/**
 * WebSocket message model
 */
public class Message {
  // add username String
  private String username;

  // add content String
  private String content;

  // add type string
  private String type;

  // add online count
  private Integer onlineCount;

  // create constructors

  public Message() {}

  public Message(String username, String content, String type) {
    this.username = username;
    this.content = content;
    this.type = type;
  }

  // define Getters and Setters
  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }


  public String getOnlineCount() {
    return Integer.toString(onlineCount);
  }

  public void setOnlineCount(int onlineCount) {
    this.onlineCount = onlineCount;
  }
}
