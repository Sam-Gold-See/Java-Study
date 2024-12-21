package Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ClientLoginUI extends JFrame {

	JTextArea consoleArea = new JTextArea();

	JTextArea usernameInputArea = new JTextArea();
	JPasswordField passwordInputArea = new JPasswordField();

	JButton loginButton = new JButton("Login");
	JButton registerButton = new JButton("Register");
	JButton exitButton = new JButton("Exit");

	public ClientLoginUI() {
		initFrame();
		initView();
		this.setVisible(true);
	}

	private void initView() {
		JLabel title = new JLabel("Client Login");
		title.setBounds(208, 20, 584, 50);
		title.setFont(new Font("Times New Roman", Font.BOLD, 32));
		this.getContentPane().add(title);

		consoleArea.setEditable(false);
		consoleArea.setBounds(50, 100, 490, 30);
		consoleArea.setFont(new Font("宋体", Font.BOLD, 24));
		this.getContentPane().add(consoleArea);

		JLabel usernameLabel = new JLabel("Username:");
		usernameLabel.setBounds(70, 170, 100, 30);
		usernameLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		this.getContentPane().add(usernameLabel);

		usernameInputArea.setBounds(180, 170, 300, 30);
		usernameInputArea.setFont(new Font("Times New Roman", Font.BOLD, 24));
		this.getContentPane().add(usernameInputArea);

		JLabel passwordLabel = new JLabel("Password:");
		passwordLabel.setBounds(70, 270, 100, 30);
		passwordLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		this.getContentPane().add(passwordLabel);

		passwordInputArea.setBounds(180, 270, 300, 30);
		passwordInputArea.setFont(new Font("Times New Roman", Font.BOLD, 24));
		this.getContentPane().add(passwordInputArea);

		loginButton.setBounds(70, 350, 120, 30);
		loginButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
		this.getContentPane().add(loginButton);

		loginButton.addActionListener(e -> {
			String username = usernameInputArea.getText();
			String password = new String(passwordInputArea.getPassword());
			if (username.isEmpty() || password.isEmpty())
				setMsg("登录时用户名或密码不能为空");
			else {
				Client.login(username, password);
				usernameInputArea.setText("");
				passwordInputArea.setText("");
			}
		});

		registerButton.setBounds(230, 350, 120, 30);
		registerButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
		this.getContentPane().add(registerButton);

		registerButton.addActionListener(e -> {
			String username = usernameInputArea.getText();
			String password = new String(passwordInputArea.getPassword());
			if (username.isEmpty() || password.isEmpty())
				setMsg("注册时用户名或密码不能为空");
			else {
				Client.register(username, password);
				usernameInputArea.setText("");
				passwordInputArea.setText("");
			}
		});

		exitButton.setBounds(380, 350, 120, 30);
		exitButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
		this.getContentPane().add(exitButton);

		exitButton.addActionListener(e -> Client.exit());

		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e){
				Client.exit();
			}
		});
	}

	private void initFrame() {
		this.setSize(600, 600);
		this.setTitle("UDPChat Client Login");
		this.setAlwaysOnTop(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.getContentPane().setBackground(new Color(212, 212, 212));
	}

	public void setMsg(String msg) {
		consoleArea.setText(msg + "\n");
	}
}
