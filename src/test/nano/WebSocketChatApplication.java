package edu.udacity.java.nano;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
@RestController
public class WebSocketChatApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebSocketChatApplication.class, args);
    }

    /**
     * Returns the login view when the user accesses the base route.
     * @return
     */
    @GetMapping("/")
    public ModelAndView login() {

        return new ModelAndView("/login");

    }

    /**
     * Returns the chat page when the user logs in.
     * @param username - The current user's username.
     * @param request - The server request accompanying the login.
     * @return ModelAndView object
     * @throws UnknownHostException
     */
    @GetMapping("/chat")
    public ModelAndView index(String username, HttpServletRequest request) throws UnknownHostException {

        // create ModelAndView
        ModelAndView mv = new ModelAndView("/chat");

        // add username to ModelAndView
        mv.addObject("username", username);

        // thanks to my reviewer for helping me with this piece.
        mv.addObject("webSocketUrl", "ws://"
            + InetAddress.getLocalHost().getHostAddress()
            + ":"
            + request.getServerPort()
            + request.getContextPath()
            + "/chat");

        // return ModelAndView
        return mv;

    }
}
