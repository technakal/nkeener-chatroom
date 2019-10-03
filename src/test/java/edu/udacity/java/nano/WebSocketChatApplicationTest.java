package edu.udacity.java.nano;

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
@WebMvcTest(WebSocketChatApplication.class)
public class WebSocketChatApplicationTest {

  @Autowired
  private MockMvc mockApp;

  /**
   * Test 1 - Access login page
   * Verify server responds with 200.
   * Verify view name is "login".
   * @throws Exception
   */
  @Test
  public void getLoginPage() throws Exception {

    System.out.println("Test 1 Starting - Access Login Page");
    System.out.println("===================================");

    this.mockApp.perform(get("/"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(view().name("login"));

    System.out.println("Test 1 Completed.");
  }

  /**
   * Test 2 - Access chat page
   * Verify server responds with 200.
   * Verify view name is "chat".
   * Verify username matches expected input.
   * @throws Exception
   */
  @Test
  public void getChatPage() throws Exception {

    System.out.println("Test 2 Starting - Access Chat Page");
    System.out.println("==================================");

    String testUser = "TestBot";

    this.mockApp.perform(get("/chat?username=" + testUser))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(view().name("chat"))
        .andExpect(model().attribute("username", "TestBot"));

    System.out.println("Test 2 Completed.");

  }
}
