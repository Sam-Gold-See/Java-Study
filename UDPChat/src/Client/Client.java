package Client;

import Server.Server;

import java.io.*;
import java.net.Socket;

public class Client {
	private static Socket socket;
	private static BufferedWriter bw;
	private static BufferedReader br;

	public static String loginUsername;

	public static ClientLoginUI clientLoginUI;
	public static ClientChatUI clientChatUI;

	public static void main(String[] args) {
		clientLoginUI = new ClientLoginUI();
		init();
	}

	private static void init() {
		try {
			socket = new Socket("127.0.0.1", Server.serverPort);
			bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException ioe) {
			System.err.println("初始化失败");
		}
		clientLoginUI.setMsg("欢迎使用 UDPChat");
	}

	public static void login(String username, String password) {
		socketWrite("/login");
		socketWrite("username=" + username + "&password=" + password);
		String result = socketRead();
		if (!result.equals("登录成功")) {
			clientLoginUI.setMsg(result);
		} else {
			loginUsername = username;
			clientLoginUI.setVisible(false);
			clientChatUI = new ClientChatUI();
			chat();
		}
	}

	public static void chat() {
		String[] usernameList = socketRead().split("&");
		for (String username : usernameList)
			clientChatUI.addUserListContent(username);
		new Thread(new ClientChatRunnable(socket,loginUsername)).start();
	}

	public static void register(String username, String password) {
		socketWrite("/register");
		socketWrite("username=" + username + "&password=" + password);
		String result = socketRead();
		clientLoginUI.setMsg(result);
	}

	public static void exit() {
		socketWrite("/exit");
		try {
			socket.close();
		} catch (IOException e) {
			System.err.println("关闭连接错误");
		}
		System.exit(0);
	}

	public static void socketWrite(String msg) {
		try {
			bw.write(msg);
			bw.newLine();
			bw.flush();
		} catch (IOException ioe) {
			System.err.println("网络流写入失败");
		}
	}

	public static String socketRead() {
		String msg = "";
		try {
			msg = br.readLine();
		} catch (IOException ioe) {
			System.err.println("网络流读入失败");
		}
		return msg;
	}
}
