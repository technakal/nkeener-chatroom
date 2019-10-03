package edu.udacity.java.nano;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class WebSocketChatServerTest {

  @Autowired
  private Environment env;

  @Autowired
  private WebTestClient client;

  private String BASE_URI = "http://localhost:8080/";
  private String USERNAME = "TestBot";
  private String CHAT_URI = BASE_URI + "chat?username=" + USERNAME;

  // todo: Login —> This could be a simple test to verify that after entering with a username, this indeed opens the chat endpoint.
  @Test
  public void testChatLogin() throws Exception {
    this.client.get().uri("/").exchange().expectStatus().isOk();
  }

  // todo: User join —> check the user counter and make sure it shows the correct number of connected users.
  @Test
  public void testOnlineUserCountAtLogin() throws Exception {

  }

  // todo: Chat -> If you'd like to go further, you might also want to test the sending / receiving of messages.
  @Test
  public void testSendMessage() throws Exception {

  }

  // todo: Leave —> Verify that logout returns to the login page. This could be achieved through a Logout / Exit button from the chat page, for example.
  @Test
  public void testUserLogOut() throws Exception {

  }

}
