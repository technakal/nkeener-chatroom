# Chat Room

## Overview

A real-time chat application using WebSocket and Spring Boot.

## Technologies

- Maven
- Spring Boot
- Gson

## Running the Application

- You can run the application using the following command:

```shell
mvn package spring-boot:run
```

## Requirements

### WebSocketChatApplication.java

- Runs the Spring Boot application.
  - Normal Spring Boot application file.
  - No changes necessary.
- Acts as the Controller for the REST calls.

#### Mapping

- login page: /
- chat: /chat

### login.html

- Implement login functionality.

### Message.java

- Implement model Message with the following attributes:
  - username
  - content
  - type
- Create constructors
  - empty constructor
  - regular constructor
- Create getters and setters
  - username
  - content
  - type

### WebSocketChatServer.java

- Define annotations
  - @OnOpen
    - Increase the user count in the room by 1.
    - Create a message containing the updated count.
    - Distribute the message to all users.
  - @OnClose
    - Decrease the user count in the room by 1.
    - Create a message containing the updated count.
    - Distribute the message to all users.
  - @OnMessage
    - Create a message containing the username, text content, type of message, and user count.
    - Distribute the message to all users.
  - sendMessageToAll()
    - For each session, send the supplied message containing username, text content, type of message, and user count.

## Rubric

- Build a basic chat room.
    - Build a spring boot application with spring boot plugins.
    - Build chat message model.
    - Build chat room controller
    - Build a login and chat room interface
- Package and run.
  - Manual test chat room application.
  - Package chat room application into jar and run it.
  - Change the default configuration according to requirement and repackage the application.
- Build unit tests.
    - Make sure to cover login, user join, chat and leave.
    - Repackage the application and make sure tests are passing.

## Acknowledgement

- Thanks to Zayah117 for the [excellent video](https://www.youtube.com/watch?v=xkxjLPvLcEI&list=WL&index=6&t=0s) introduction to this project. Without his explanation of the requirements, I never would have finished this.
- Thanks to adtya9876, whose [Github repo](https://github.com/adtya9876/Chatroom) helped get me through some tough spots. Especially WebSocketChatServer.sendMessageToAll and unit tests. 