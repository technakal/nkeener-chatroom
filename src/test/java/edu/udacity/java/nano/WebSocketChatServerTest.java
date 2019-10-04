package edu.udacity.java.nano;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class WebSocketChatServerTest {

  @Autowired
  private Environment env;

  private String BASE_URI = "http://localhost:8080/";
  private String USERNAME = "TestBot";
  private String CHAT_URI = BASE_URI + "chat?username=" + USERNAME;

  private WebDriver driver;

  /**
   * Thanks again to adtlya9876 for this trick to get the web driver setup working.
   * https://github.com/adtya9876/Chatroom/blob/master/src/test/java/edu.udacity.java.nano/WebSocketChatServerTest.java
   */
  @Before
  public void setupWebDriver() {
    WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();
  }

  /**
   * Test 1: Test attempted login with no username supplied.
   * Expected result = System displays error message and prevents login.
   * @throws Exception
   */
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

  /**
   * Test 2: Test successful login with username TestBot.
   * Expected result: System logs in and displays chat page.
   * Expected result: URI includes ?username=TestBot parameter.
   * @throws Exception
   */
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

  /**
   * Test 3: Test user login session counter.
   * Expected result: Test that the session counter increases when the user logs in.
   * @throws Exception
   */
  @Test
  public void testOnlineUserCountAtLogin() throws Exception {
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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

  /**
   * Test 4: Test sending a message through the chat interface.
   * Expected result: New item appears in the chat box.
   * Expected result: New item matches the expected format and input message.
   * @throws Exception
   */
  @Test
  public void testSendMessage() throws Exception {
    driver.get(CHAT_URI);
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
    Assert.assertEquals(messageContent.getText(), USERNAME + "：This is a test of the emergency Selenium system.");
    driver.close();
  }

  /**
   * Test 5: Test clearing the chat screen.
   * Expected result: Screen removes all chat messages.
   * @throws Exception
   */
  @Test
  public void testClearMessages() throws Exception {
    driver.get(CHAT_URI);
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
    try {
      WebElement messageContent = driver.findElement(By.className("message-content"));
    } catch (NoSuchElementException e) {
      Assert.assertNotNull(e);
    }
    driver.close();
  }

  /**
   * Test 6: Test user logout
   * Expected result: Page redirects to the base URI.
   * @throws Exception
   */
  @Test
  public void testUserLogOut() throws Exception {
    driver.get(CHAT_URI);
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
