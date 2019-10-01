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
@WebMvcTest
public class WebSocketChatApplicationTest {

  @Autowired
  private MockMvc testApp;

  @Test
  public void testLoginPage() throws Exception {
    this.testApp.perform(get("/"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(view().name("/login"));
  }

  @Test
  public void testUserLogin() throws Exception {
    this.testApp.perform(get("/chat?username=TestBot"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(view().name("/chat"))
        .andExpect(model().attribute("username", "TestBot"));
  }
}
