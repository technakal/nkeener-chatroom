# Chat Room

## Overview

A real-time chat application using WebSocket and Spring Boot.

## Technologies

## Installation

## Running the Application

- You can run the application using the following command:

```shell
mvn build; mvn spring-boot:run
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
    -
  - @OnClose
    -
  - @OnMessage
    -
  - sendMessageToAll()
