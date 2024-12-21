# UDP-Chat

这是一个使用 JavwSwing 制作的聊天室软件，使用 Java 开发，软件逻辑以及界面设计均为本人原创、个人实现

## 如何运行我的项目

首先，下载所有文件。然后，确保你的操作环境基于 JDK 17 或更高版本。程序依赖于相对文件路径，所以请避免不必要地更改文件。

本项目的启动文件分别是 `UDPChat\src\Client.java`（客户端） 和 `UDPChat\src\Server.java`（服务器），通过 DCP 协议链接，实现聊天功能，所以需要先运行服务器文件，再运行客户端文件。

## 项目结构和文件组织

本节提供了项目目录结构和文件组织的概述。它帮助用户和贡献者了解项目中各种组件的位置以及文件的组织方式。

项目目录结构如下：

```
UDPChat
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
│   │       ClientChatUI.java
│   │       ClientLoginUI.java
│   │
│   ├── server
│   │       Server.java
│   │       ServerChatRunnable.java
│   │       ServerUI.java
│   │
│   └── user
│           User.java
│           userinfo.properties
```

## 描述每个文件和目录的作用

1. **Client 目录**

   - **Client.java**：这是客户端程序的入口点。它负责启动客户端的主线程，初始化必要的资源（如网络连接），并创建客户端的用户界面。

   - **ClientChatRunnable.java**：这个类实现了 Runnable 接口，作为客户端的聊天线程。它的主要职责是监听来自服务器的消息，并将这些消息显示在客户端的界面上。

   - **ClientChatUI.java**：这个类定义了客户端的聊天用户界面。它包含了图形组件和处理用户输入的逻辑，以便用户可以发送和接收消息。

   - **ClientLoginUI.java**：这个类定义了客户端的登录用户界面。它允许用户输入他们的凭证（如用户名和密码），然后尝试连接到服务器并进行身份验证。

2. **Server 目录**

   - **Server.java**：这是服务器程序的入口点。它负责启动服务器的主线程，初始化网络服务和创建服务器的用户界面。

   - **ServerChatRunnable.java**：这个类也实现了 Runnable 接口，作为服务器的聊天线程。它的作用是接收来自各个客户端的消息，并将这些消息广播给所有的客户端。

   - **ServerUI.java**：这个类定义了服务器的用户界面。它提供了对服务器状态的监控和管理功能。

3. **User 目录**

   - **User.java**：这是一个用户类，负责存储用户信息，包括用户名、密码等。

   - **userInfo.properties**：这是一个属性文件，用于存储用户的信息。这通常是键值对的集合，其中键代表不同的用户属性，而值则是相应的属性值。存储了本地用户信息，以便在程序运行期间可以快速访问。

## 其他说明

这个项目代码均本人依照项目逻辑自行编写。如果你愿意，可以使用整个项目。我希望它也能帮助你有效地学习 Java。

如果你想联系我，可以发送电子邮件到 `chunxin.huang@m.scnu.edu.cn`。

# UDP-Chat

This is a chat room software created using Java Swing, developed in Java. Both the logic and interface design of the software are original and implemented by myself.

## How to Run My Project

Firstly, download all the files. Then, ensure your operating environment is based on JDK 17 or a higher version. The program depends on relative file paths, so avoid unnecessary changes to the files.

The startup files for this project are `UDPChat\src\Client.java` (client) and `UDPChat\src\Server.java` (server), which are linked through the DCP protocol to implement chat functionality. Therefore, you need to run the server file first, and then run the client file.

## Project Structure and File Organization

This section provides an overview of the project directory structure and file organization. It helps users and contributors understand the location of various components in the project and the organization of files.
The project directory structure is as follows:

```
UDPChat
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
│   │       ClientChatUI.java
│   │       ClientLoginUI.java
│   │
│   ├── server
│   │       Server.java
│   │       ServerChatRunnable.java
│   │       ServerUI.java
│   │
│   └── user
│           User.java
│           userinfo.properties
```

## Description of the Purpose of Each File and Directory

1. **Client Directory**

   - **Client.java**: This is the entry point for the client program. It is responsible for starting the main thread of the client, initializing necessary resources (such as network connections), and creating the user interface for the client.

   - **ClientChatRunnable.java**: This class implements the Runnable interface and serves as the chat thread for the client. Its main responsibility is to listen for messages from the server and display these messages on the client's interface.

   - **ClientChatUI.java**: This class defines the chat user interface for the client. It contains graphical components and logic for handling user input, allowing users to send and receive messages.

   - **ClientLoginUI.java**: This class defines the login user interface for the client. It allows users to enter their credentials (such as username and password) and then attempts to connect to the server and authenticate.

2. **Server Directory**

   - **Server.java**: This is the entry point for the server program. It is responsible for starting the main thread of the server, initializing network services, and creating the user interface for the server.

   - **ServerChatRunnable.java**: This class also implements the Runnable interface and serves as the chat thread for the server. Its role is to receive messages from each client and broadcast these messages to all clients.

   - **ServerUI.java**: This class defines the user interface for the server. It provides monitoring and management capabilities for the server's status.

3. **User Directory**

   - **User.java**: This is a user class responsible for storing user information, including username and password.

   - **userInfo.properties**: This is a properties file used to store user information. It is typically a collection of key-value pairs, where the keys represent different user attributes and the values are the corresponding attribute values. It stores local user information for quick access during program runtime.

## Additional Notes

All the code for this project is written by myself according to the project logic. If you wish, you can use the entire project. I hope it can also help you learn Java effectively.

If you would like to contact me, you can send an email to `chunxin.huang@m.scnu.edu.cn`.
