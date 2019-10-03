package edu.udacity.java.nano;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WebSocketChatServerTest {

  @Autowired
  private Environment env;

  private String BASE_URI = "http://localhost:8080/";
  private String USERNAME = "TestBot";
  private String CHAT_URI = BASE_URI + "chat?username=" + USERNAME;

  private WebDriver driver;

  /**
   * Thanks again to adtlya9876 for this trick to get the web driver setup working.
   */
  @Before
  public void setupWebDriver() {
    WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();
  }

  // todo: Login -> Test failed login (no username supplied)
  @Test
  public void testFailedLogin() throws Exception {
    driver.get(BASE_URI);
    WebElement submitButton = driver.findElement(By.id("submitButton"));
    submitButton.click();
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    String currentURI = driver.getCurrentUrl();
    Assert.assertEquals(currentURI, String.format(BASE_URI));
    driver.close();
  }

  // todo: Login —> This could be a simple test to verify that after entering with a username, this indeed opens the chat endpoint.
  @Test
  public void testChatLogin() throws Exception {
    driver.get(BASE_URI);
    WebElement userNameInput = driver.findElement(By.id("username"));
    WebElement submitButton = driver.findElement(By.id("submitButton"));
    userNameInput.sendKeys(USERNAME);
    submitButton.click();
    try {
      Thread.sleep(2000);
    } catch(InterruptedException e) {
      e.printStackTrace();
    }
    String currentURI = driver.getCurrentUrl();
    Assert.assertEquals(currentURI, CHAT_URI);
    driver.close();
  }

  // todo: User join —> check the user counter and make sure it shows the correct number of connected users.
  @Test
  public void testOnlineUserCountAtLogin() throws Exception {
    driver.get(BASE_URI);
    WebElement userNameInput = driver.findElement(By.id("username"));
    WebElement submitButton = driver.findElement(By.id("submitButton"));
    userNameInput.sendKeys(USERNAME);
    submitButton.click();
    try {
      Thread.sleep(2000);
    } catch(InterruptedException e) {
      e.printStackTrace();
    }
    WebElement userCount = driver.findElement(By.className("chat-num"));
    Assert.assertEquals(userCount.getText(), "1");
    driver.close();
  }

  // todo: Chat -> If you'd like to go further, you might also want to test the sending / receiving of messages.
  @Test
  public void testSendMessage() throws Exception {
    driver.get(BASE_URI);
    WebElement messageBox = driver.findElement(By.id("msg"));
    WebElement sendButton = driver.findElement(By.id("send-msg"));
    messageBox.sendKeys("This is a test of the emergency Selenium system.");
    sendButton.click();
    try {
      Thread.sleep(2000);
    } catch(InterruptedException e) {
      e.printStackTrace();
    }
    WebElement messageContent = driver.findElement(By.className("message-content"));
    Assert.assertEquals(messageContent.getText(), "This is a test of the emergency Selenium system.");
    driver.close();
  }

  // todo: Clear -> Verify that pressing clear removes all messages from the screen.
  @Test
  public void testClearMessages() throws Exception {
    driver.get(BASE_URI);
    WebElement messageBox = driver.findElement(By.id("msg"));
    WebElement sendButton = driver.findElement(By.id("send-msg"));
    WebElement clearButton = driver.findElement(By.id("clear-msg"));
    messageBox.sendKeys("This is a test of the emergency Selenium system.");
    sendButton.click();
    try {
      Thread.sleep(2000);
    } catch(InterruptedException e) {
      e.printStackTrace();
    }
    clearButton.click();
    try {
      Thread.sleep(2000);
    } catch(InterruptedException e) {
      e.printStackTrace();
    }
    WebElement messageContent = driver.findElement(By.className("message-content"));
    Assert.assertNull(messageContent);
    driver.close();
  }

  // todo: Leave —> Verify that logout returns to the login page. This could be achieved through a Logout / Exit button from the chat page, for example.
  @Test
  public void testUserLogOut() throws Exception {
    driver.get(BASE_URI);
    WebElement logoutButton = driver.findElement(By.id("logOutButton"));
    logoutButton.click();
    try {
      Thread.sleep(2000);
    } catch(InterruptedException e) {
      e.printStackTrace();
    }
    String currentURI = driver.getCurrentUrl();
    Assert.assertEquals(currentURI, BASE_URI);
    driver.close();
  }

}
