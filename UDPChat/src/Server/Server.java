package Server;

import User.User;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Properties;

public class Server {
	private static ServerSocket serverSocket;

	public final static ArrayList<User> userInfoList = new ArrayList<>();
	public final static File userInfoFile = new File("src/User/userInfo.properties");

	public static ArrayList<Socket> clientSocketList = new ArrayList<>();
	public static final int serverPort = 9999;
	public static ServerUI serverUI;

	public static void main(String[] args) {
		serverUI = new ServerUI();
		initUserInfoList();
		initServer();
		while (true) {
			try {
				Socket socket = serverSocket.accept();
				new Thread(new ServerChatRunnable(socket)).start();
			} catch (IOException ioe) {
				System.err.println("建立连接失败");
			}
		}
	}

	private static void initUserInfoList() {
		Properties userProperties = new Properties();

		if (!userInfoFile.exists()) {

			try {
				userInfoFile.getParentFile().mkdirs();
				userInfoFile.createNewFile();
			} catch (IOException ioe) {
				System.err.println("创建用户信息文件失败");
			}

			try {
				User admin = new User("admin", "123456");
				User root = new User("root", "123456");
				User twx = new User("twx", "DSB");
				userProperties.setProperty("username", admin.getUsername() + "," + root.getUsername() + "," + twx.getUsername());
				userProperties.setProperty("password", admin.getPassword() + "," + root.getPassword() + "," + twx.getPassword());
				userProperties.store(new FileOutputStream(userInfoFile), null);
				userInfoList.add(admin);
				userInfoList.add(root);
				userInfoList.add(twx);
			} catch (IOException ioe) {
				System.err.println("初始化用户信息文件失败");
			}

		} else {

			try {
				userProperties.load(new FileInputStream(userInfoFile));
			} catch (IOException ioe) {
				System.err.println("读取用户信息文件失败");
			}

			String[] usernameList = userProperties.getProperty("username").split(",");
			String[] passwordList = userProperties.getProperty("password").split(",");
			for (int i = 0; i < usernameList.length; i++)
				userInfoList.add(new User(usernameList[i], passwordList[i]));
		}
		serverUI.addMsg("本地用户信息读取成功");
	}

	private static void initServer() {
		try {
			serverSocket = new ServerSocket(serverPort);
		} catch (IOException ioe) {
			System.err.println("服务器接收端口设置失败");
		}

		serverUI.addMsg("服务器启动成功");
	}
}
