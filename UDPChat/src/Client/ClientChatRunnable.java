package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientChatRunnable implements Runnable {
	private Socket serverSocket;
	private String username;
	private boolean stop;
	private BufferedReader br;

	public ClientChatRunnable(Socket socket, String username) {
		this.serverSocket = socket;
		this.username = username;
		stop = false;
		try {
			br = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
		} catch (IOException ioe) {
			System.err.println("网络流读入有问题");
		}
	}

	@Override
	public void run() {
		while (!stop) {
			String message = "";
			try {
				message = br.readLine();
			} catch (IOException ioe) {
				System.err.println("网络流读入有问题");
			}
			String toUser = message.split("@")[1].split(":")[0];
			if (toUser.equals("All") || toUser.equals(username))
				Client.clientChatUI.addChatContent(message);
		}
	}
}
