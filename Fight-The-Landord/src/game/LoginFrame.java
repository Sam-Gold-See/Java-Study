package game;

import domain.User;
import util.Code;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class LoginFrame extends JFrame implements MouseListener {

	static ArrayList<User> users = new ArrayList<>();

	static {
		users.add(new User("admin", "123456"));
		users.add(new User("guest", "123456"));
	}

	private static final String loginImagePath = "image/login/";

	JTextField username = new JTextField();
	JPasswordField password = new JPasswordField();
	JTextField code = new JTextField();
	JLabel rightCode = new JLabel();
	JButton login = new JButton();
	JButton register = new JButton();
	JButton check = new JButton();

	public LoginFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("image/poker/dizhu.png"));

		initJFrame();

		initView();

		this.setVisible(true);
	}

	private void initJFrame() {
		this.setSize(633, 423);
		this.setTitle("斗地主游戏 v1.0 登录");
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setAlwaysOnTop(true);
		this.setLayout(null);
	}

	private void initView() {

		JLabel usernameText = new JLabel("用户名");
		Font usernameFont = new Font(null, Font.BOLD, 16);
		usernameText.setFont(usernameFont);
		usernameText.setForeground(Color.WHITE);
		usernameText.setBounds(140, 50, 55, 22);
		this.getContentPane().add(usernameText);

		username.setBounds(225, 45, 200, 30);
		this.getContentPane().add(username);

		JLabel passwordText = new JLabel("密码");
		Font passwordFont = new Font(null, Font.BOLD, 16);
		passwordText.setFont(passwordFont);
		passwordText.setForeground(Color.WHITE);
		passwordText.setBounds(200, 95, 40, 22);
		this.getContentPane().add(passwordText);

		password.setEchoChar('*');
		password.setBounds(260, 90, 160, 30);
		this.getContentPane().add(password);

		JLabel codeText = new JLabel("验证码");
		Font codeFont = new Font(null, Font.BOLD, 16);
		codeText.setFont(codeFont);
		codeText.setForeground(Color.WHITE);
		codeText.setBounds(220, 140, 55, 22);
		this.getContentPane().add(codeText);

		code.setBounds(290, 135, 100, 30);
		this.getContentPane().add(code);

		String codeStr = Code.getCode();
		Font rightCodeFont = new Font(null, Font.BOLD, 16);
		rightCode.setForeground(Color.RED);
		rightCode.setFont(rightCodeFont);
		rightCode.setText(codeStr);
		rightCode.addMouseListener(this);
		rightCode.setBounds(400, 135, 100, 30);
		this.getContentPane().add(rightCode);

		login.setBounds(210, 330, 128, 45);
		login.setIcon(new ImageIcon(loginImagePath + "login.png"));
		login.setBorderPainted(false);
		login.setContentAreaFilled(false);
		login.addMouseListener(this);
		this.getContentPane().add(login);

		register.setBounds(350, 330, 128, 45);
		register.setIcon(new ImageIcon(loginImagePath + "register.png"));
		register.setBorderPainted(false);
		register.setContentAreaFilled(false);
		register.addMouseListener(this);
		this.getContentPane().add(register);

		check.setBounds(430, 90, 18, 29);
		check.setIcon(new ImageIcon(loginImagePath + "check.png"));
		check.setBorderPainted(false);
		check.setContentAreaFilled(false);
		check.addMouseListener(this);
		this.getContentPane().add(check);

		JLabel background = new JLabel(new ImageIcon(loginImagePath + "background.png"));
		background.setBounds(0, 0, 633, 423);
		this.getContentPane().add(background);
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		Object obj = e.getSource();
		if (obj == login) {
			String usernameInput = username.getText();
			String passwordInput = new String(password.getPassword());
			String codeInput = code.getText();

			User userInput = new User(usernameInput, passwordInput);

			if (codeInput.isEmpty())
				showDialog("验证码不能为空");
			else if (usernameInput.isEmpty() || passwordInput.isEmpty())
				showDialog("用户名或密码为空");
			else if (!codeInput.equalsIgnoreCase(rightCode.getText()))
				showDialog("验证码输入错误");
			else if (contains(userInput)) {
				this.setVisible(false);
				new GameFrame();
			} else
				showDialog("用户名或密码错误");
		} else if (obj == register)
			showDialog("注册功能仍未实装");
		else if (obj == rightCode) {
			String codeStr = Code.getCode();
			rightCode.setText(codeStr);
			code.setText("");
		}
	}

	public void showDialog(String content) {
		JDialog jDialog = new JDialog();
		jDialog.setSize(200, 150);
		jDialog.setAlwaysOnTop(true);
		jDialog.setLocationRelativeTo(null);
		jDialog.setModal(true);

		JLabel warning = new JLabel(content);
		warning.setBounds(0, 0, 200, 150);
		jDialog.getContentPane().add(warning);

		jDialog.setVisible(true);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getSource() == login)
			login.setIcon(new ImageIcon(loginImagePath + "loginPressed.png"));
		else if (e.getSource() == register)
			register.setIcon(new ImageIcon(loginImagePath + "registerPressed.png"));
		else if (e.getSource() == check) {
			check.setIcon(new ImageIcon(loginImagePath + "checkPressed.png"));
			password.setEchoChar((char) 0);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getSource() == login)
			login.setIcon(new ImageIcon(loginImagePath + "login.png"));
		else if (e.getSource() == register)
			register.setIcon(new ImageIcon(loginImagePath + "register.png"));
		else if (e.getSource() == check) {
			check.setIcon(new ImageIcon(loginImagePath + "check.png"));
			password.setEchoChar('*');
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	public boolean contains(User userInput) {
		for (User rightUser : users)
			if (rightUser.getUsername().equals(userInput.getUsername()) && rightUser.getPassword().equals(userInput.getPassword()))
				return true;
		return false;
	}
}
