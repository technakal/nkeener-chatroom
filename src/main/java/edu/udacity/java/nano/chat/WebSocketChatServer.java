package edu.udacity.java.nano.chat;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
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

  /**
   * JSON manipulator
   */
  private Gson gson = new Gson();
  private JsonParser jsonParser = new JsonParser();

  /**
   * Distributes the message to all users.
   * @param message - the Json message to distribute to all users.
   */
  private static void sendMessageToAll(String message) {
    // distribute the content of the msg parameter to all connected clients.
    onlineSessions.forEach((id, session) -> {
      try {
        session.getBasicRemote().sendText(message);
      } catch(IOException e) {
        e.printStackTrace();
      }
    });
  }

  /**
   * Open connection, 1) add session, 2) add user.
   */
  @OnOpen
  public void onOpen(Session session, @PathParam("username") String username) {
    // add session to onlineSessions map
    onlineSessions.put(session.getId(), session);

    // todo: get the @PathParam to work
    // create new Message object containing the onlineSession size to update all user sessions with the accurate number.
    Message userEnter = new Message( username,"Entering the room: ", "ENTER", onlineSessions.size());

    // send message item with updated count to everyone
    sendMessageToAll(gson.toJson(userEnter));

  }

  /**
   * Send message, 1) get username and session, 2) send message to all.
   */
  @OnMessage
  public void onMessage(Session session, String jsonStr) {
    // create new Message object from the jsonStr parameter.
    JsonObject json = jsonParser.parse(jsonStr).getAsJsonObject();
    JsonElement jsonElem = gson.toJsonTree(json);
    jsonElem.getAsJsonObject().addProperty("type", "SPEAK");
    jsonElem.getAsJsonObject().addProperty("onlineCount", onlineSessions.size());
    jsonStr = gson.toJson(jsonElem);
    Message newUserMessage = gson.fromJson(jsonStr, Message.class);

    // distribute message to all
    sendMessageToAll(gson.toJson(newUserMessage));
  }

  /**
   * Close connection, 1) remove session, 2) update user.
   */
  @OnClose
  public void onClose(Session session, @PathParam("username") String username) {
    // remove session from onlineSessions map
    onlineSessions.remove(session.getId());

    // create new Message object containing the onlineSession size to update all user sessions with the accurate number.
    Message userLeave = new Message( username,"Leaving the room: ", "LEAVE", onlineSessions.size());

    // send message item with updated count to everyone
    sendMessageToAll(gson.toJson(userLeave));

  }

  /**
   * Print exception.
   */
  @OnError
  public void onError(Session session, Throwable error) {
    error.printStackTrace();
  }

}
