# Daily-Note

这是一个简单的本地笔记软件，使用 Java 开发，软件逻辑以及界面设计来源于 itheima，其余有关代码由个人实现。

## 如何运行我的项目

首先，下载所有文件。然后，确保你的操作环境基于 JDK 17 或更高版本。程序依赖于相对文件路径，所以请避免不必要地更改文件。

本项目的主要启动文件是 `Daily-Note\src\App.java`。

## 项目结构和文件组织

本节提供了项目目录结构和文件组织的概述。它帮助用户和贡献者了解项目中各种组件的位置以及文件的组织方式。

项目目录结构如下：
```
Daily-Note
  |
  └─ src
      │  App.java
      │
      └─ software
          ├─ note
          │      Note.java
          │
          ├─ ui
          │      AddFrame.java
          │      NoteFrame.java
          │      UpdateFrame.java
          │
          └─ util
                  Util.java
```

## 描述每个文件和目录的用途

`src\App.java`: 应用程序的主要入口点。

`src\software\note\Note.java`: 笔记的Javabean类，用于格式化存储笔记信息。

`src\software\ui\AddFrame.java`: 添加笔记的窗口。

`src\software\ui\NoteFrame.java`: 查看笔记的窗口。

`src\software\ui\UpdateFrame.java`: 更新笔记的窗口。

`src\software\util\Util.java`: 工具类，用于实现文件读写、弹窗生成等功能。

## 功能

### 主界面：

1. 初始化界面
2. 初始化菜单
3. 读取本地笔记数据
4. 显示界面整体布局
5. 显示本地笔记数据
6. 提供添加、删除、修改笔记的功能按钮
7. 提供选取已存在的笔记功能
8. 检查修改本地笔记数据功能触发合法性
9. 检查删除本地笔记数据功能触发合法性
10. 本地笔记数据导出到桌面功能
11. 桌面笔记数据导入到本地功能

### 添加界面：

1. 初始化界面
2. 提供标题、内容的输入栏
3. 检查输入内容是否合法
4. 提供保存、取消按钮
5. 保存笔记信息到本地
6. 返回到主界面功能

### 修改界面：

1. 初始化输入栏
2. 将输入栏内容设置为选中笔记聂荣
3. 提供标题、内容的输入栏
4. 检查输入内容是否合法
5. 提供修改、取消按钮
6. 保存笔记信息到本地
7. 返回到主界面功能

### 工具类：

1. 实现本地文件读取功能
2. 实现本地文件写入功能
3. 实现弹窗生成功能
4. 实现压缩本地文件数据到桌面功能
5. 实现读取桌面文件数据解压到本地功能

## 其他说明

这个项目基于 itheima，其中界面代码、项目逻辑由itheima提供，其余代码均本人依照项目逻辑自行编写。如果你愿意，可以使用整个项目。我希望它也能帮助你有效地学习 Java。

如果你想联系我，可以发送电子邮件到 `chunxin.huang@m.scnu.edu.cn`。

# Daily-Note

This is a simple local note-taking software developed using Java. The logic and interface design of the software are sourced from itheima, while the rest of the code is implemented by the individual.

## How to Run My Project

First, download all the files. Then, make sure your operating environment is based on JDK 17 or a higher version. The program depends on relative file paths, so avoid unnecessary changes to files.

The main startup file for this project is `Daily-Note\src\App.java`.

## Project Structure and File Organization

This section provides an overview of the project directory structure and file organization. It helps users and contributors understand the location of various components in the project and how the files are organized.

The project directory structure is as follows:

```
Daily-Note
  |
  └─ src
      │  App.java
      │
      └─ software
          ├─ note
          │      Note.java
          │
          ├─ ui
          │      AddFrame.java
          │      NoteFrame.java
          │      UpdateFrame.java
          │
          └─ util
                  Util.java
```

## Description of the Purpose of Each File and Directory

`src\App.java`: The main entry point of the application.

`src\software\note\Note.java`: The JavaBean class for notes, used for formatting and storing note information.

`src\software\ui\AddFrame.java`: The window for adding notes.

`src\software\ui\NoteFrame.java`: The window for viewing notes.

`src\software\ui\UpdateFrame.java`: The window for updating notes.

`src\software\util\Util.java`: A utility class that implements functions such as file reading/writing and dialog generation.

## Features

### Main Interface:

1. Initialize the interface
2. Initialize the menu
3. Read local note data
4. Display the overall layout of the interface
5. Display local note data
6. Provide function buttons for adding, deleting, and modifying notes
7. Provide the functionality to select existing notes
8. Check the legality of triggering the modification of local note data
9. Check the legality of triggering the deletion of local note data
10. Export local note data to the desktop
11. Import note data from the desktop to the local

### Add Interface:

1. Initialize the interface
2. Provide input fields for title and content
3. Check the legality of the input content
4. Provide save and cancel buttons
5. Save note information to the local
6. Return to the main interface functionality

### Update Interface:

1. Initialize the input fields
2. Set the content of the input fields to the selected note
3. Provide input fields for title and content
4. Check the legality of the input content
5. Provide modify and cancel buttons
6. Save note information to the local
7. Return to the main interface functionality

### Utility Class:

1. Implement local file reading functionality
2. Implement local file writing functionality
3. Implement dialog generation functionality
4. Implement compression of local file data to the desktop
5. Implement reading desktop file data and decompressing it to the local

## Other Instructions

This project is based on itheima, where the interface code and project logic are provided by itheima, and the rest of the code is written by myself according to the project logic. If you wish, you can use the entire project. I hope it also helps you learn Java effectively.

If you would like to contact me, you can send an email to `chunxin.huang@m.scnu.edu.cn`.
