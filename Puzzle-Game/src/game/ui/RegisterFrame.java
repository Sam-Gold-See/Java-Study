package game.ui;

import game.user.User;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class RegisterFrame extends JFrame implements MouseListener {

	String registerPath = "image/register/";

	JTextField registerName = new JTextField();
	JPasswordField password1 = new JPasswordField();
	JPasswordField password2 = new JPasswordField();

	JButton seePassword1 = new JButton();
	JButton seePassword2 = new JButton();
	JButton register = new JButton();
	JButton reset = new JButton();
	JButton login = new JButton();

	int flagSeePassword1 = 0;
	int flagSeePassword2 = 0;

	public RegisterFrame() {
		initFrame();

		initView();

		this.setVisible(true);
	}

	private void initFrame() {
		this.setTitle("Puzzle Game RegisterFrame v1.0");
		this.setSize(488, 430);
		this.setAlwaysOnTop(true);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void initView() {
		JLabel registerNameText = new JLabel(new ImageIcon(registerPath + "注册用户名.png"));
		registerNameText.setBounds(100, 135, 79, 17);
		this.getContentPane().add(registerNameText);

		registerName.setBounds(195, 135, 200, 30);
		this.getContentPane().add(registerName);

		JLabel registerPasswordText1 = new JLabel(new ImageIcon(registerPath + "注册密码.png"));
		registerPasswordText1.setBounds(100, 195, 64, 17);
		this.getContentPane().add(registerPasswordText1);

		password1.setBounds(195, 195, 200, 30);
		password1.setEchoChar('*');
		this.getContentPane().add(password1);

		seePassword1.setBounds(400, 195, 18, 29);
		seePassword1.setIcon(new ImageIcon(registerPath + "显示密码.png"));
		seePassword1.setBorderPainted(false);
		seePassword1.setContentAreaFilled(false);
		seePassword1.addMouseListener(this);
		this.getContentPane().add(seePassword1);

		JLabel registerPasswordText2 = new JLabel(new ImageIcon(registerPath + "再次输入密码.png"));
		registerPasswordText2.setBounds(90, 256, 96, 17);
		this.getContentPane().add(registerPasswordText2);

		password2.setBounds(195, 256, 200, 30);
		password2.setEchoChar('*');
		this.getContentPane().add(password2);

		seePassword2.setBounds(400, 256, 18, 29);
		seePassword2.setIcon(new ImageIcon(registerPath + "显示密码.png"));
		seePassword2.setBorderPainted(false);
		seePassword2.setContentAreaFilled(false);
		seePassword2.addMouseListener(this);
		this.getContentPane().add(seePassword2);

		login.setBounds(34, 310, 128, 47);
		login.setIcon(new ImageIcon(registerPath + "登录按钮.png"));
		login.setBorderPainted(false);
		login.setContentAreaFilled(false);
		login.addMouseListener(this);
		this.getContentPane().add(login);

		reset.setBounds(165, 310, 128, 47);
		reset.setIcon(new ImageIcon(registerPath + "重置按钮.png"));
		reset.setBorderPainted(false);
		reset.setContentAreaFilled(false);
		reset.addMouseListener(this);
		this.getContentPane().add(reset);

		register.setBounds(296, 310, 128, 47);
		register.setIcon(new ImageIcon(registerPath + "注册按钮.png"));
		register.setBorderPainted(false);
		register.setContentAreaFilled(false);
		register.addMouseListener(this);
		this.getContentPane().add(register);


		JLabel background = new JLabel(new ImageIcon(registerPath + "background.png"));
		background.setBounds(0, 0, 470, 390);
		this.getContentPane().add(background);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == seePassword1) {
			if (flagSeePassword1 == 0) {
				flagSeePassword1++;
				seePassword1.setIcon(new ImageIcon(registerPath + "显示密码按下.png"));
				String passwordInput1 = new String(this.password1.getPassword());
				password1.setText(passwordInput1);
				password1.setEchoChar((char) 0);
			} else {
				flagSeePassword1--;
				password1.setEchoChar('*');
				seePassword1.setIcon(new ImageIcon(registerPath + "显示密码.png"));
			}
		} else if (e.getSource() == seePassword2) {
			if (flagSeePassword2 == 0) {
				flagSeePassword2++;
				seePassword2.setIcon(new ImageIcon(registerPath + "显示密码按下.png"));
				String passwordInput1 = new String(this.password2.getPassword());
				password2.setText(passwordInput1);
				password2.setEchoChar((char) 0);
			} else {
				flagSeePassword2--;
				password2.setEchoChar('*');
				seePassword2.setIcon(new ImageIcon(registerPath + "显示密码.png"));
			}
		} else if (e.getSource() == login) {
			this.setVisible(false);
			new LoginFrame();
		} else if (e.getSource() == reset) {
			registerName.setText("");
			password1.setText("");
			password2.setText("");
		} else if (e.getSource() == register) {
			String usernameInput = this.registerName.getText();
			String passwordInput1 = new String(this.password1.getPassword());
			String passwordInput2 = new String(this.password2.getPassword());

			if (usernameInput.isEmpty())
				showDialog("用户名不能为空");
			else if (passwordInput1.isEmpty() || passwordInput2.isEmpty())
				showDialog("密码不能为空");
			else if (contains(usernameInput))
				showDialog("用户名已存在");
			else if (!passwordInput1.equals(passwordInput2)) {
				showDialog("两次密码不一致");
			} else {
				LoginFrame.users.add(new User(usernameInput, passwordInput1));
				BufferedWriter bw;
				try {
					bw = new BufferedWriter(new FileWriter(LoginFrame.userinfoFile, true));
					bw.write("username=" + usernameInput + "&password=" + passwordInput1);
					bw.newLine();
					bw.close();
				} catch (IOException ee) {
					ee.printStackTrace();
				}
				showDialog("注册成功");
				this.setVisible(false);
				new LoginFrame();
			}
		}
	}

	public static void showDialog(String content) {
		JDialog jDialog = new JDialog();
		jDialog.setSize(200, 150);
		jDialog.setLocationRelativeTo(null);
		jDialog.setAlwaysOnTop(true);
		jDialog.setModal(true);

		JLabel warnings = new JLabel(content);
		warnings.setBounds(0, 0, 200, 150);
		jDialog.getContentPane().add(warnings);

		jDialog.setVisible(true);
	}

	public boolean contains(String usernameInput) {
		for (User alreadyUser : LoginFrame.users)
			if (usernameInput.equals(alreadyUser.getUserName()))
				return true;
		return false;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getSource() == reset) {
			reset.setIcon(new ImageIcon(registerPath + "重置按下.png"));
		} else if (e.getSource() == register) {
			register.setIcon(new ImageIcon(registerPath + "注册按下.png"));
		} else if (e.getSource() == login) {
			login.setIcon(new ImageIcon(registerPath + "登录按下.png"));
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getSource() == reset) {
			reset.setIcon(new ImageIcon(registerPath + "重置按钮.png"));
		} else if (e.getSource() == register) {
			register.setIcon(new ImageIcon(registerPath + "注册按钮.png"));
		} else if (e.getSource() == login) {
			login.setIcon(new ImageIcon(registerPath + "登录按钮.png"));
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
}
