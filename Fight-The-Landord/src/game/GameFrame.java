package game;

import domain.Poker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class GameFrame extends JFrame implements ActionListener {

	public static Container container = null;

	JButton[] landlord = new JButton[2];

	JButton[] publishPoker = new JButton[2];

	JLabel dizhu;

	ArrayList<ArrayList<Poker>> currentList = new ArrayList<>();

	ArrayList<ArrayList<Poker>> playerList = new ArrayList<>();

	ArrayList<Poker> lordList = new ArrayList<>();

	ArrayList<Poker> pokerList = new ArrayList<>();

	JTextField[] time = new JTextField[3];

	private static final String imagePath = "image/poker/";

	public GameFrame() {

		setIconImage(Toolkit.getDefaultToolkit().getImage(imagePath + "dizhu.png"));

		initFrame();

		initView();

		this.setVisible(true);

		initCard();

		initGame();
	}

	public void initFrame() {
		this.setTitle("斗地主");
		this.setSize(830, 620);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		container = this.getContentPane();
		container.setLayout(null);
		container.setBackground(Color.GRAY);
	}

	public void initView() {
		JButton robButton = new JButton("抢地主");
		robButton.setBounds(320, 400, 75, 20);
		robButton.addActionListener(this);
		robButton.setVisible(false);
		landlord[0] = robButton;
		container.add(robButton);

		JButton noButton = new JButton("不  抢");
		noButton.setBounds(420, 400, 75, 20);
		noButton.addActionListener(this);
		noButton.setVisible(false);
		landlord[1] = noButton;
		container.add(noButton);

		JButton outPokerButton = new JButton("出牌");
		outPokerButton.setBounds(320, 400, 60, 20);
		outPokerButton.addActionListener(this);
		outPokerButton.setVisible(false);
		publishPoker[0] = outPokerButton;
		container.add(outPokerButton);

		JButton noOutPoker = new JButton("不要");
		noOutPoker.setBounds(420, 400, 60, 20);
		noOutPoker.addActionListener(this);
		noOutPoker.setVisible(false);
		publishPoker[1] = noOutPoker;
		container.add(noOutPoker);

		for (int i = 0; i < 3; i++) {
			time[i] = new JTextField("倒计时:");
			time[i].setEditable(false);
			time[i].setVisible(false);
			container.add(time[i]);
		}
		time[0].setBounds(140, 230, 60, 20);
		time[1].setBounds(375, 360, 60, 20);
		time[2].setBounds(620, 230, 60, 20);

		dizhu = new JLabel(new ImageIcon(imagePath + "dizhu.png"));
		dizhu.setVisible(false);
		dizhu.setSize(40, 40);
		container.add(dizhu);
	}

	public void initCard() {
		for (int i = 1; i <= 5; i++) {
			for (int j = 1; j <= 13; j++)
				if (i == 5 && j > 2)
					break;
				else {
					Poker poker = new Poker(i + "-" + j, false);
					poker.setLocation(370, 180);
					pokerList.add(poker);
					container.add(poker);
				}
		}

		Collections.shuffle(pokerList);

		ArrayList<Poker> player0 = new ArrayList<>();
		ArrayList<Poker> player1 = new ArrayList<>();
		ArrayList<Poker> player2 = new ArrayList<>();

		for (int i = 0; i < pokerList.size(); i++) {
			Poker poker = pokerList.get(i);
			if (i <= 2) {
				lordList.add(poker);
				Common.move(poker, poker.getLocation(), new Point(270 + (75 * i), 10));
			} else if (i % 3 == 0) {
				player0.add(poker);
				Common.move(poker, poker.getLocation(), new Point(50, 60 + i * 5));
			} else if (i % 3 == 1) {
				poker.turnFront();
				player1.add(poker);
				Common.move(poker, poker.getLocation(), new Point(180 + i * 7, 450));
			} else {
				player2.add(poker);
				Common.move(poker, poker.getLocation(), new Point(700, 60 + i * 5));
			}
			container.setComponentZOrder(poker, 0);
		}

		playerList.add(player0);
		playerList.add(player1);
		playerList.add(player2);

		for (int i = 0; i < 3; i++) {
			order(playerList.get(i));
			Common.rePosition(this, playerList.get(i), i);
		}
	}

	public void order(ArrayList<Poker> player) {
		player.sort((o1, o2) -> {
			int value1 = o1.getValue();
			int value2 = o2.getValue();

			if (value1 == value2)
				return o2.getColor() - o1.getColor();
			else
				return value1 - value2;
		});
	}

	public void initGame() {
		for (int i = 0; i < 3; i++) {
			ArrayList<Poker> list = new ArrayList<>();
			currentList.add(list);
		}

		landlord[0].setVisible(true);
		landlord[1].setVisible(true);

		for (JTextField field : time) {
			field.setText("倒计时30秒");
			field.setVisible(true);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}
}
