package edu.udacity.java.nano.chat;

import com.google.gson.Gson;

/**
 * WebSocket message model
 */
public class Message {
  // add username String
  private String username;

  // add content String
  private String msg;

  // add type string
  private String type;

  // add online count
  private Integer onlineCount;

  // create constructors

  public Message() {}

  public Message(String username, String content, String type, int onlineCount) {
    this.username = username;
    this.msg = content;
    this.type = type;
    this.onlineCount = onlineCount;
  }

  // define Getters and Setters
  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getContent() {
    return msg;
  }

  public void setContent(String content) {
    this.msg = content;
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
