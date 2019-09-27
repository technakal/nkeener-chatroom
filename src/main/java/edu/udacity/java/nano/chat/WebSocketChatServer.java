package edu.udacity.java.nano.chat;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * WebSocket Server
 * @see ServerEndpoint WebSocket Client
 * @see Session   WebSocket Session
 */

@Component
@ServerEndpoint("/chat")
public class WebSocketChatServer {

  /**
   * All chat sessions.
   */
  private static Map<String, Session> onlineSessions = new ConcurrentHashMap<>();

  private static void sendMessageToAll(String msg) {
    // todo: distribute the content of the msg parameter to all connected clients.

  }

  /**
   * Open connection, 1) add session, 2) add user.
   */
  @OnOpen
  public void onOpen(Session session) {
    // todo: add session to onlineSessions map
    onlineSessions.put(session.toString(), session);

    // todo: create new Message object containing the onlineSession size to update all user sessions with the accurate number.
    Message userCount = new Message();
    userCount.setOnlineCount(onlineSessions.size());

    // todo: send updated count to everyone with sendMessageToAll(message);
    sendMessageToAll(userCount.getOnlineCount());

  }

  /**
   * Send message, 1) get username and session, 2) send message to all.
   */
  @OnMessage
  public void onMessage(Session session, String jsonStr) {
    // todo: create new Message object with the jsonStr parameter.
    JsonObject json = new JsonParser().parse(jsonStr).getAsJsonObject();
    String username = json.get("username").getAsString();
    String msg = json.get("msg").getAsString();
    Message userMsg = new Message(username, msg, "SPEAK");

    // todo: distribute message to all using sendMessageToAll()

  }

  /**
   * Close connection, 1) remove session, 2) update user.
   */
  @OnClose
  public void onClose(Session session) {
    // todo: remove session from onlineSessions map
    onlineSessions.remove(session.toString());

    // todo: create new Message object containing the onlineSession size to update all user sessions with the accurate number.
    Message userCount = new Message();
    userCount.setOnlineCount(onlineSessions.size());

    // todo: send updated count to everyone with sendMessageToAll(message)
    sendMessageToAll(userCount.getOnlineCount());

  }

  /**
   * Print exception.
   */
  @OnError
  public void onError(Session session, Throwable error) {
    error.printStackTrace();
  }

}
