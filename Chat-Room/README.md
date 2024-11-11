# Chat-Room

这是一个简单的基于控制台的聊天室软件，使用 Java 开发，软件逻辑以及界面设计来源于 itheima，其余有关代码由个人实现。

## 如何运行我的项目

首先，下载所有文件。然后，确保你的操作环境基于 JDK 17 或更高版本。程序依赖于相对文件路径，所以请避免不必要地更改文件。

本项目的启动文件分别是 `Chat-Room\src\Client.java`（客户端） 和 `Chat-Room\src\Server.java`（服务器），通过DCP协议链接，实现聊天功能，所以需要先运行服务器文件，再运行客户端文件。

## 项目结构和文件组织

本节提供了项目目录结构和文件组织的概述。它帮助用户和贡献者了解项目中各种组件的位置以及文件的组织方式。

项目目录结构如下：

```
Chat-Room
|
├── .idea
│
├── local
│
├── out
│
├── src
│   ├── client
│   │       Client.java
│   │       ClientChatRunnable.java
│   │       
│   ├── server
│   │       Server.java
│   │       ServerChatRunnable.java
│   │       
│   └── user
│           User.java
```

## 描述每个文件和目录的用途

`src\client\Client.java`：客户端程序入口，负责启动客户端线程，并创建客户端窗口。

`src\client\ClientChatRunnable.java`：客户端聊天线程，负责接收服务器消息并显示在客户端窗口。

`src\server\Server.java`：服务器程序入口，负责启动服务器线程，并创建服务器窗口。

`src\server\ServerChatRunnable.java`：服务器聊天线程，负责接收客户端消息并广播给所有客户端。

`src\user\User.java`：用户类，负责存储用户信息，包括用户名、密码等。

## 功能

### 客户端 (Client.java)

- **主界面 (main方法)**

  - 初始化客户端，包括创建Socket连接和输入输出流。

  - 显示欢迎信息和主菜单，允许用户选择登录、注册或退出。

- **初始化方法 (init)**

  - 创建到服务器的Socket连接。

  - 初始化BufferedWriter和BufferedReader用于网络通信。

- **主菜单方法 (frame)**

  - 提供一个循环，让用户可以不断进行操作，直到选择退出。

- **登录方法 (login)**

  - 发送登录命令到服务器。

  - 读取用户输入的用户名和密码，发送到服务器验证。

  - 如果登录成功，启动一个线程用于聊天功能。

- **注册方法 (register)**

  - 发送注册命令到服务器。

  - 读取用户输入的用户名和密码，发送到服务器进行注册。

  - 显示注册结果。

- **退出方法 (exit)**

  - 发送退出命令到服务器。

  - 关闭Socket连接和Scanner对象。

  - 退出程序。

- **用户信息输入方法 (userinfoInput)**

  - 读取用户输入的用户名和密码。

- **网络写入方法 (socketWrite)**

  - 将消息写入到服务器。

- **网络读取方法 (socketRead)**

  - 从服务器读取消息。

### 客户端聊天线程 (ClientChatRunnable.java)

- **聊天线程 (run方法)**

  - 在一个单独的线程中运行，不断从服务器读取消息。

  - 如果收到退出命令，停止线程。

  - 如果消息不是自己发送的，则打印消息。

### 服务器 (Server.java)

- **主界面 (main方法)**

  - 初始化服务器，包括创建ServerSocket。

  - 不断接受客户端连接，并为每个连接创建一个新线程。

- **初始化方法 (init)**

  - 创建服务器目录和用户信息文件。

  - 读取用户信息文件，初始化用户列表。

### 服务器聊天线程 (ServerChatRunnable.java)

- **聊天线程 (run方法)**

  - 处理客户端发送的命令，如登录、注册和退出。

  - 如果登录成功，启动聊天功能。

  - 在聊天功能中，读取客户端发送的消息，并将其广播给所有其他客户端。

- **登录验证方法 (checkLogin)**

  - 验证用户名和密码是否匹配。

- **注册验证方法 (checkRegister)**

  - 验证用户名和密码是否符合注册要求。

- **更新用户信息方法 (updateUserinfo)**

  - 将新用户信息写入到用户信息文件。

- **网络写入方法 (socketWrite)**

  - 将消息写入到客户端。

- **网络读取方法 (socketRead)**

  - 从客户端读取消息。

### 用户类 (User.java)

- **用户类**

  - 表示一个用户，包含用户名和密码。

  - 提供了equals和hashCode方法，以便在集合中使用。

构成了一个简单的聊天室应用程序，其中客户端可以登录、注册和发送消息，服务器则处理这些请求并将消息广播给所有连接的客户端。

## 其他说明

这个项目基于 itheima，其中界面代码、项目逻辑由itheima提供，其余代码均本人依照项目逻辑自行编写。如果你愿意，可以使用整个项目。我希望它也能帮助你有效地学习 Java。

如果你想联系我，可以发送电子邮件到 `chunxin.huang@m.scnu.edu.cn`。

# Chat-Room

This is a simple console-based chat room software developed in Java. The logic and interface design of the software are sourced from itheima, and the rest of the related codes are implemented by myself.

## How to Run My Project

Firstly, download all the files. Then, ensure your operating environment is based on JDK 17 or a higher version. The program depends on relative file paths, so avoid unnecessary changes to the files.

The startup files for this project are `Chat-Room\src\Client.java` (client) and `Chat-Room\src\Server.java` (server), which are linked through the DCP protocol to implement chat functionality. Therefore, you need to run the server file first, and then run the client file.

## Project Structure and File Organization

This section provides an overview of the project directory structure and file organization. It helps users and contributors understand the location of various components in the project and the organization of files.

The project directory structure is as follows:

```
Chat-Room
|
├── .idea
│
├── local
│
├── out
│
├── src
│   ├── client
│   │       Client.java
│   │       ClientChatRunnable.java
│   │       
│   ├── server
│   │       Server.java
│   │       ServerChatRunnable.java
│   │       
│   └── user
│           User.java
```

## Description of the Purpose of Each File and Directory

`src\client\Client.java`: The entry point for the client program, responsible for starting the client thread and creating the client window.

`src\client\ClientChatRunnable.java`: The client chat thread, responsible for receiving server messages and displaying them in the client window.

`src\server\Server.java`: The entry point for the server program, responsible for starting the server thread and creating the server window.

`src\server\ServerChatRunnable.java`: The server chat thread, responsible for receiving client messages and broadcasting them to all clients.

`src\user\User.java`: The user class, responsible for storing user information, including username and password, etc.

## Features


### Client (Client.java)

- **Main Interface (main method)**

  - Initializes the client, including creating the Socket connection and input/output streams.

  - Displays welcome information and the main menu, allowing users to choose to log in, register, or exit.

- **Initialization Method (init)**

  - Creates a Socket connection to the server.


  - Initializes BufferedWriter and BufferedReader for network communication.

- **Main Menu Method (frame)**

  - Provides a loop that allows users to perform operations continuously until they choose to exit.

- **Login Method (login)**

  - Sends the login command to the server.

  - Reads the username and password entered by the user and sends them to the server for verification.

  - If the login is successful, starts a thread for chatting functionality.

- **Registration Method (register)**

  - Sends the registration command to the server.

  - Reads the username and password entered by the user and sends them to the server for registration.

  - Displays the registration result.

- **Exit Method (exit)**

  - Sends the exit command to the server.

  - Closes the Socket connection and the Scanner object.

  - Exits the program.

- **User Information Input Method (userinfoInput)**
  - Reads the username and password entered by the user.

- **Network Write Method (socketWrite)**

  - Writes messages to the server.

- **Network Read Method (socketRead)**

  - Reads messages from the server.

### Client Chat Thread (ClientChatRunnable.java)

- **Chat Thread (run method)**

  - Runs in a separate thread, continuously reading messages from the server.

  - If an exit command is received, stops the thread.

  - If the message is not sent by the user themselves, prints the message.

### Server (Server.java)

- **Main Interface (main method)**

  - Initializes the server, including creating the ServerSocket.

  - Continuously accepts client connections and creates a new thread for each connection.

- **Initialization Method (init)**

  - Creates server directories and user information files.

  - Reads the user information file and initializes the user list.

### Server Chat Thread (ServerChatRunnable.java)

- **Chat Thread (run method)**

  - Handles commands sent by the client, such as login, register, and exit.

  - If the login is successful, initiates the chat functionality.

  - In the chat functionality, reads messages sent by the client and broadcasts them to all other connected clients.

- **Login Verification Method (checkLogin)**

  - Verifies if the username and password match.

- **Registration Verification Method (checkRegister)**

  - Verifies if the username and password meet the registration requirements.

- **Update User Information Method (updateUserinfo)**

  - Writes new user information to the user information file.

- **Network Write Method (socketWrite)**

  - Writes messages to the client.

- **Network Read Method (socketRead)**

  - Reads messages from the client.

### User Class (User.java)

- **User Class**

  - Represents a user, containing a username and password.

  - Provides `equals` and `hashCode` methods for use in collections.

This constitutes a simple chat room application where clients can log in, register, and send messages, while the server processes these requests and broadcasts messages to all connected clients.

## Additional Notes

This project is based on itheima, with the interface code and project logic provided by itheima, and the rest of the code written by myself according to the project logic. If you wish, you can use the entire project. I hope it also helps you learn Java effectively.

If you would like to contact me, you can send an email to `chunxin.huang@m.scnu.edu.cn`.