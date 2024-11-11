package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientChatRunnable implements Runnable {
	private Socket serverSocket;
	private String username;
	private boolean stop;

	public ClientChatRunnable(Socket socket, String username) {
		this.serverSocket = socket;
		this.username = username;
		stop = false;
	}

	@Override
	public void run() {
		while (!stop) {
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
				String message = br.readLine();
				if (message.equals("/exit")) {
					stop = true;
				} else {
					String usernameInput = message.split("@")[0];
					if (!username.equals(usernameInput))
						System.out.println(message);
				}
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}
}
