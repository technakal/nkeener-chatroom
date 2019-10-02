package edu.udacity.java.nano;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest
public class WebSocketChatApplicationTest {

  @Autowired
  private MockMvc testApp;

  /**
   * Test Case 1: When a user accesses the site, it renders the login.html page.
   */
  @Test
  public void testLoginPage() throws Exception {
    System.out.println("Performing Test Case 1");
    System.out.println("======================");
    this.testApp.perform(get("/"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(view().name("/login"));
  }

  /**
   * Test Case 2: When a user logs into the site, the site renders the chat.html page.
   * Test Case 3: When a user logs into the site using the name "TestBot", the site model stores TestBot to the username attribute.
   */
  @Test
  public void testUserLogin() throws Exception {
    System.out.println("Performing Test Case 2 and 3");
    System.out.println("============================");
    this.testApp.perform(get("/chat?username=TestBot"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(view().name("/chat"))
        .andExpect(model().attribute("username", "TestBot"));
  }
}
