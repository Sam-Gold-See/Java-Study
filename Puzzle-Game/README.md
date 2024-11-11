# Puzzle-Game

这是一个简单的本地拼图游戏，使用 Java 开发，也是我的第一个 Java 项目，学习资料来源于 itheima。

## 如何运行我的项目

首先，下载所有文件。然后，确保你的操作环境基于 JDK 17 或更高版本。程序依赖于相对文件路径，所以请避免不必要地更改文件。

本项目的主要启动文件是 `PuzzleGame\src\App.java`。

## 项目结构和文件组织

本节提供了项目目录结构和文件组织的概述。它帮助用户和贡献者了解项目中各种组件的位置以及文件的组织方式。

项目目录结构如下：
```
PuzzleGame
  |
  ├─ image
  |
  └─ src
      │  App.java
      │
      └─ game
          ├─ checkcode
          │      CheckCode.java
          │
          ├─ ui
          │      GameFrame.java
          │      LoginFrame.java
          │      RegisterFrame.java
          │
          └─ user
                  User.java
```

## 描述每个文件和目录的用途

`src\App.java`: 应用程序的主要入口点。

`src\game\checkcode\CheckCode.java`：用于提供登录界面所需的验证码

`src\game\ui\GameFrame.java`：用于实现游戏界面以及相应功能

`src\game\ui\LoginFrame.java`：用于实现登录界面以及相应功能

`src\game\ui\RegisterFrame.java`：用于实现注册界面以及相应功能

`src\game\user\User.java`：是一个 javabean 类，用于提供用户类

## 功能

### 登录界面：

1. 初始化登录界面
2. 初始化登录界面功能按钮
3. 初始化本地存储文件
4. 从本地文件读取用户信息
5. 生成、更改验证码
6. 提供输入用户名、密码、验证码文本框
7. 长按显示已输入密码内容
8. 验证输入用户信息是否和已存储用户信息匹配
9. 进入游戏界面
10. 按钮反馈动画
11. 跳转到注册界面

### 注册界面：

1. 初始化注册界面
2. 初始化注册界面功能按钮
3. 提供输入用户名、密码的文本框
4. 单击切换是否显示密码明文
5. 验证输入信息无误后将用户信息加入用户信息库
6. 存储用户信息到本地文件
7. 清空文本框已输入内容
8. 按钮反馈动画
9. 跳转到登录界面

### 游戏界面：

1. 随机初始化游戏图片
2. 使用方向键进行游戏
3. 键入`A`查看完整图片
4. 键入`W`立即胜利
5. 实现界面目录、按钮
6. 更改游戏图片
7. 重玩当前游戏图片
8. 存档当前游玩进度
9. 读取存档文件并恢复游戏进度（会清除存档文件）
10. 退出登录
11. 结束程序

## 其他说明

这个项目基于 itheima，其中部分代码为依照项目逻辑自行编写。如果你愿意，可以使用整个项目。我希望它也能帮助你有效地学习 Java。

如果你想联系我，可以发送电子邮件到 `chunxin.huang@m.scnu.edu.cn`。

# Puzzle-Game

An easy local puzzle game made with Java, and also my first Java project, learned from itheima.

## How to Run My Project

Firstly, download all the files. Then, ensure your operating environment is based on JDK 17 or a later version. The program relies on relative file paths, so please avoid changing any files unnecessarily.

The main startup file for this project is `PuzzleGame\src\App.java`.

## Project Structure and File Organization

This section provides an overview of the project's directory structure and file organization. It helps users and contributors understand where to find various components of the project and how the files are organized.

The project directory structure is as follows:

```
PuzzleGame
  |
  ├─image...
  |
  └─src
      │  App.java
      │
      └─game
          ├─checkcode
          │      CheckCode.java
          │
          ├─ui
          │      GameFrame.java
          │      LoginFrame.java
          │      RegisterFrame.java
          │
          └─user
                  User.java
```

## Describing the Purpose of Each File and Directory

`src\App.java`: The main entry point of the application.

`src\game\checkcode\CheckCode.java`：Provides the verification code needed for the login interface.

`src\game\ui\GameFrame.java`：Implements the game interface and its functionality.

`src\game\ui\LoginFrame.java`：Implements the login interface and its functionality.

`src\game\ui\RegisterFrame.java`：Implements the registration interface and its functionality.

`src\game\user\User.java`：A JavaBean class used to provide user information.

## Function

### Login Interface:

1. Initialize the login interface.
2. Initialize login interface functionality buttons.
3. Initialize local storage file.
4. Read user information from the local file.
5. Generate or change the verification code.
6. Provide text fields for entering username, password, and verification code.
7. Long press to display the entered password.
8. Validate if the input user information matches the stored user information.
9. Enter the game interface.
10. Button feedback animation.
11. Navigate to the registration interface.

### Registration Interface:

1. Initialize the registration interface.
2. Initialize registration interface functionality buttons.
3. Provide text fields for entering username and password.
4. Click to toggle password visibility.
5. After verifying the input information, add the user information to the user database.
6. Store user information to the local file.
7. Clear the text fields.
8. Button feedback animation.
9. Navigate to the login interface.

### Game Interface:

1. Randomly initialize game images.
2. Use arrow keys to play the game.
3. Press A to view the full image.
4. Press W to win immediately.
5. Implement interface directory and buttons.
6. Change the game image.
7. Replay the current game image.
8. Save the current game progress.
9. Read the save file and restore the game progress (the save file will be deleted).
10. Log out.
11. Exit the program.


## Proviso

This project is based on itheima, some of the codes are written by myself according to the project logic. You can use the entire project if you wish. I hope it also helps you study Java effectively.

If you want to contact me, you can email `chunxin.huang@m.scnu.edu.cn`.