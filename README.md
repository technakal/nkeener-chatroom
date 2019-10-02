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

## Acknowledgement

- Thanks to Zayah117 for the [excellent video](https://www.youtube.com/watch?v=xkxjLPvLcEI&list=WL&index=6&t=0s) introduction to this project. Without his explanation of the requirements, I never would have finished this.
