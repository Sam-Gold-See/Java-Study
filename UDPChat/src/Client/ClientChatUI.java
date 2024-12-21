package Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ClientChatUI extends JFrame {

	JTextArea userListText = new JTextArea();

	JTextArea chatContainerText = new JTextArea();

	JTextArea userInputText = new JTextArea();

	JTextArea chatInputText = new JTextArea();

	JButton sendButton = new JButton("Send");

	public ClientChatUI() {
		initFrame();

		initView();

		this.setVisible(true);
	}

	private void initView() {
		JLabel title = new JLabel("Client Chat");
		title.setBounds(208, 20, 584, 50);
		title.setFont(new Font("Times New Roman", Font.BOLD, 32));
		this.getContentPane().add(title);

		JLabel usernameLabel = new JLabel(Client.loginUsername);
		usernameLabel.setBounds(30, 50, 100, 50);
		usernameLabel.setFont(new Font("Times New Roman", Font.BOLD, 32));
		this.getContentPane().add(usernameLabel);

		JLabel userListTitle = new JLabel("User List");
		userListTitle.setBounds(440, 60, 200, 50);
		userListTitle.setFont(new Font("Times New Roman", Font.BOLD, 28));
		this.getContentPane().add(userListTitle);

		userListText.setEditable(false);
		userListText.setBounds(450, 100, 100, 350);
		userListText.setFont(new Font("宋体", Font.BOLD, 24));
		this.getContentPane().add(userListText);

		chatContainerText.setEditable(false);
		chatContainerText.setBounds(50, 100, 350, 300);
		chatContainerText.setFont(new Font("宋体", Font.BOLD, 24));
		this.getContentPane().add(chatContainerText);

		JScrollPane scrollPane = new JScrollPane(chatContainerText);
		scrollPane.setBounds(50, 100, 350, 300);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.getContentPane().add(scrollPane);

		JLabel userInputTitle = new JLabel("@");
		userInputTitle.setBounds(40, 430, 40, 30);
		userInputTitle.setFont(new Font("Times New Roman", Font.BOLD, 28));
		this.getContentPane().add(userInputTitle);

		userInputText.setBounds(80, 430, 320, 30);
		userInputText.setFont(new Font("Times New Roman", Font.BOLD, 24));
		this.getContentPane().add(userInputText);

		chatInputText.setBounds(50, 480, 350, 50);
		chatInputText.setFont(new Font("宋体", Font.BOLD, 20));
		this.getContentPane().add(chatInputText);

		sendButton.setBounds(450, 470, 100, 50);
		sendButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
		this.getContentPane().add(sendButton);

		sendButton.addActionListener(e -> {
			String toUser = userInputText.getText();
			String content = chatInputText.getText();
			String msg = Client.loginUsername + "@" + toUser + ":" + content;
			Client.socketWrite(msg);
			if (!(toUser.equals(Client.loginUsername) || toUser.equals("All")))
				addChatContent(msg);
			chatInputText.setText("");
		});

		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				Client.exit();
			}
		});
	}

	private void initFrame() {
		this.setSize(600, 600);
		this.setTitle("UDPChat Client Chat");
		this.setAlwaysOnTop(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.getContentPane().setBackground(new Color(212, 212, 212));
	}

	public void addChatContent(String content) {
		this.chatContainerText.append(content + "\n");
	}

	public void addUserListContent(String content) {
		this.userListText.append(content + "\n");
	}
}
