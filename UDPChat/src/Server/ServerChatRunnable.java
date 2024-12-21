package Server;

import Client.Client;
import User.User;

import java.io.*;
import java.net.Socket;
import java.util.Properties;

public class ServerChatRunnable implements Runnable {
	private static int count = 1;

	private final Socket clientSocket;
	private final int number;
	private boolean exit;
	private BufferedReader br;
	private BufferedWriter bw;

	public User user;

	public ServerChatRunnable(Socket socket) {
		clientSocket = socket;
		user = new User();
		number = count;
		exit = false;
		try {
			bw = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
			br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		} catch (IOException ioe) {
			System.err.println("初始化输入输出流错误");
		}
	}

	@Override
	public void run() {
		Server.serverUI.addMsg("线程" + (count++) + "启动");
		while (!exit) {
			String command = socketRead();
			switch (command) {
				case "/login" -> {
					String[] userInfo = this.getUserinfo();
					User newUser = new User(userInfo[0], userInfo[1]);
					if (checkLogin(newUser)) {
						socketWrite("登录成功");
						user.setUsername(userInfo[0]);
						Server.serverUI.addMsg(user.getUsername() + "在线程" + number + "登录");
						Server.clientSocketList.add(clientSocket);
						chat();
					} else {
						socketWrite("登录失败");
					}
				}
				case "/register" -> {
					String[] userInfo = this.getUserinfo();
					updateProperties(userInfo);
					Server.serverUI.addMsg("线程" + number + "注册账号（" + updateUserInfo(userInfo) + "）成功");
					socketWrite("注册成功，请自行登录");
				}
				case "/exit" -> {
					exit = true;
					Server.serverUI.addMsg("线程" + number + "关闭");
				}
			}
		}
	}

	private void chat() {
		StringBuilder sb = new StringBuilder("All");
		for (User user : Server.userInfoList)
			sb.append("&").append(user.getUsername());
		socketWrite(sb.toString());
		while (!exit) {
			String command = socketRead();
			Server.serverUI.addMsg(command);
			if (command.equals("/exit")) {
				exit = true;
				Server.clientSocketList.remove(clientSocket);
				Server.serverUI.addMsg(user.getUsername() + "在线程" + number + "退出");
			} else {
				for (Socket socket : Server.clientSocketList)
					socketWrite(command, socket);
			}
		}
	}

	private boolean checkLogin(User newUser) {
		boolean flag = false;
		for (User user : Server.userInfoList)
			if (user.equals(newUser)) {
				flag = true;
				break;
			}
		return flag;
	}

	private String[] getUserinfo() {
		String userInfoReceive = "";
		try {
			userInfoReceive = br.readLine();
		} catch (IOException ioe) {
			System.err.println("网络流读取错误");
		}
		String[] userInfo = new String[2];
		String[] userInfoParts = userInfoReceive.split("&");
		userInfo[0] = userInfoParts[0].split("=")[1];
		userInfo[1] = userInfoParts[1].split("=")[1];
		return userInfo;
	}

	private User updateUserInfo(String[] userInfo) {
		User newUser = new User(userInfo[0], userInfo[1]);
		Server.userInfoList.add(newUser);
		return newUser;
	}

	private void updateProperties(String[] userInfo) {
		try {
			Properties userProperties = new Properties();
			userProperties.load(new FileInputStream(Server.userInfoFile));

			userProperties.setProperty("username", userProperties.getProperty("username") + "," + userInfo[0]);
			userProperties.setProperty("password", userProperties.getProperty("password") + "," + userInfo[1]);

			userProperties.store(new FileOutputStream(Server.userInfoFile), null);
		} catch (IOException ioe) {
			System.err.println("更新用户文件错误");
		}
	}

	private void socketWrite(String msg, Socket socket) {
		try {
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			bw.write(msg);
			bw.newLine();
			bw.flush();
		} catch (IOException ioe) {
			System.err.println("网络流写入失败");
		}
	}

	private void socketWrite(String msg) {
		try {
			bw.write(msg);
			bw.newLine();
			bw.flush();
		} catch (IOException ioe) {
			System.err.println("网络流写入失败");
		}
	}

	private String socketRead() {
		String msg = "";
		try {
			msg = br.readLine();
		} catch (IOException ioe) {
			System.err.println("网络流读入失败");
		}
		return msg;
	}
}
