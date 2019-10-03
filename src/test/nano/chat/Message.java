package edu.udacity.java.nano.chat;

/**
 * WebSocket message model
 */
public class Message {

  private String username;
  private String msg;
  private String type;
  private Integer onlineCount;

  /**
   * Empty Message constructor
   */
  public Message() {}

  /**
   * Complete message constructor
   * @param username - The username associated with the message.
   * @param content - The text of the message.
   * @param type - The type of message. Options are ENTER, LEAVE, and CHAT.
   * @param onlineCount - The number of users currently in the session at time of message.
   */
  public Message(String username, String content, String type, int onlineCount) {
    this.username = username;
    this.msg = content;
    this.type = type;
    this.onlineCount = onlineCount;
  }

  /**
   * Returns the username.
   * @return
   */
  public String getUsername() {
    return username;
  }

  /**
   * Sets the username to the input value.
   * @param username - The username.
   */
  public void setUsername(String username) {
    this.username = username;
  }

  /**
   * Returns the message text.
   * @return
   */
  public String getMsg() {
    return msg;
  }

  /**
   * Sets the message text to the input value.
   * @param content - The text of the message
   */
  public void setMsg(String content) {
    this.msg = content;
  }

  /**
   * Returns the message type
   * @return
   */
  public String getType() {
    return type;
  }

  /**
   * Sets the message type to the input value.
   * @param type - The type of message. Valid options are ENTER, LEAVE, and CHAT.
   */
  public void setType(String type) {
    this.type = type;
  }

  /**
   * Returns the number of users in the session.
   * @return
   */
  public String getOnlineCount() {
    return Integer.toString(onlineCount);
  }

  /**
   * Updates the number of users in the session.
   * @param onlineCount - The number of users currently in the session.
   */
  public void setOnlineCount(int onlineCount) {
    this.onlineCount = onlineCount;
  }
}
