package Server;

import javax.swing.*;
import java.awt.*;

public class ServerUI extends JFrame {

	JTextArea consoleArea = new JTextArea();

	public ServerUI() {
		initFrame();
		initView();
		this.setVisible(true);
	}

	private void initView() {

		JLabel title = new JLabel("Server");
		title.setBounds(240, 20, 584, 50);
		title.setFont(new Font("Times New Roman", Font.BOLD, 32));
		this.getContentPane().add(title);

		consoleArea.setEditable(false);
		consoleArea.setBounds(45, 100, 500, 400);
		consoleArea.setFont(new Font("宋体", Font.BOLD, 12));
		this.getContentPane().add(consoleArea);

		JScrollPane scrollPane = new JScrollPane(consoleArea);
		scrollPane.setBounds(45, 100, 500, 400);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.getContentPane().add(scrollPane);
	}

	private void initFrame() {
		this.setSize(600, 600);
		this.setTitle("UDPChat Server");
		this.setAlwaysOnTop(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.getContentPane().setBackground(new Color(212, 212, 212));
	}

	public void addMsg(String msg) {
		consoleArea.append(msg + "\n");
	}
}
