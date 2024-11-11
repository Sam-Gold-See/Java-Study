package game.ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class GameFrame extends JFrame implements KeyListener, ActionListener {

	int[][] data = new int[4][4];
	int[][] win = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 0}};
	int step = 0;
	Random random = new Random();
	int initRandom = random.nextInt(3);
	String[] pathName = new String[]{"animal", "girl", "sport"};
	int[] pathLen = new int[]{8, 11, 10};
	String imagePath = "image\\" + pathName[initRandom] + "\\" + pathName[initRandom] + random.nextInt(pathLen[initRandom]) + "\\";

	JMenuItem jMenuReplay = new JMenuItem("replay");
	JMenuItem jMenuReLogin = new JMenuItem("re-login");
	JMenuItem jMenuExit = new JMenuItem("exit");
	JMenuItem jMenuAccount = new JMenuItem("account");

	JMenuItem jMenuChangeAnimal = new JMenuItem("animal");
	JMenuItem jMenuChangeGirl = new JMenuItem("girl");
	JMenuItem jMenuChangeSport = new JMenuItem("sport");

	ArrayList<JMenuItem> jMenuSaves = new ArrayList<>();
	ArrayList<Boolean> jMenuSavesActive = new ArrayList<>();
	ArrayList<JMenuItem> jMenuGets = new ArrayList<>();

	JMenuItem jMenuSaveSite1 = new JMenuItem("save1");
	JMenuItem jMenuSaveSite2 = new JMenuItem("save2");
	JMenuItem jMenuSaveSite3 = new JMenuItem("save3");
	JMenuItem jMenuSaveSite4 = new JMenuItem("save4");
	JMenuItem jMenuSaveSite5 = new JMenuItem("save5");

	JMenuItem jMenuGetSite1 = new JMenuItem();
	JMenuItem jMenuGetSite2 = new JMenuItem();
	JMenuItem jMenuGetSite3 = new JMenuItem();
	JMenuItem jMenuGetSite4 = new JMenuItem();
	JMenuItem jMenuGetSite5 = new JMenuItem();

	int x = 0;
	int y = 0;

	public GameFrame() {
		initFrame();

		initJMenuBar();

		initSaveGet();

		initData();

		initImage();

		this.setVisible(true);
	}

	private void initSaveGet() {
		String localPath = "local//site";
		for (int i = 1; i <= 5; i++) {
			File siteFile = new File(localPath + i + "//save.txt");
			if (!siteFile.exists()) {
				jMenuGets.get(i - 1).setText("save" + i + "（空）");
				jMenuSaves.get(i - 1).setText("save" + i + "（空）");
				jMenuSavesActive.add(false);
			} else {
				jMenuGets.get(i - 1).setText("save" + i);
				jMenuSaves.get(i - 1).setText("save" + i);
				jMenuSavesActive.add(true);
			}
		}
	}

	private void initData() {
		int[] array = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
		for (int i = 0; i < 16; i++) {
			int index = random.nextInt(16);
			int temp = array[index];
			array[index] = array[i];
			array[i] = temp;
		}

		for (int i = 0; i < 16; i++) {
			if (array[i] == 0) {
				x = i / 4;
				y = i % 4;
			}
			data[i / 4][i % 4] = array[i];
		}
	}

	private void initImage() {
		this.getContentPane().removeAll();

		if (victory()) {
			JLabel winLabel = new JLabel(new ImageIcon("image\\win.png"));
			winLabel.setBounds(203, 283, 197, 73);
			this.getContentPane().add(winLabel);
		}

		JLabel stepCount = new JLabel("已执行步数：" + step);
		stepCount.setBounds(50, 30, 100, 20);
		this.getContentPane().add(stepCount);

		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 4; j++) {
				JLabel jLabel = new JLabel(new ImageIcon(imagePath + data[i][j] + ".jpg"));
				jLabel.setBounds(105 * j + 83, 105 * i + 134, 105, 105);
				jLabel.setBorder(new BevelBorder(BevelBorder.LOWERED));
				this.getContentPane().add(jLabel);
			}

		JLabel background = new JLabel(new ImageIcon("image\\background.png"));
		background.setBounds(40, 40, 508, 560);
		this.getContentPane().add(background);

		this.getContentPane().repaint();
	}

	public boolean victory() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (data[i][j] != win[i][j])
					return false;
			}
		}
		return true;
	}

	private void initJMenuBar() {
		JMenuBar jMenuBar = new JMenuBar();

		JMenu jMenuFunction = new JMenu("function");
		JMenu jMenuAboutUs = new JMenu("about us");
		JMenu jMenuChange = new JMenu("change");
		JMenu jMenuSave = new JMenu("saves");
		JMenu jMenuGet = new JMenu("gets");

		jMenuFunction.add(jMenuChange);
		jMenuFunction.add(jMenuSave);
		jMenuFunction.add(jMenuGet);
		jMenuFunction.add(jMenuReplay);
		jMenuFunction.add(jMenuReLogin);
		jMenuFunction.add(jMenuExit);
		jMenuAboutUs.add(jMenuAccount);

		jMenuChange.add(jMenuChangeAnimal);
		jMenuChange.add(jMenuChangeGirl);
		jMenuChange.add(jMenuChangeSport);

		jMenuSave.add(jMenuSaveSite1);
		jMenuSave.add(jMenuSaveSite2);
		jMenuSave.add(jMenuSaveSite3);
		jMenuSave.add(jMenuSaveSite4);
		jMenuSave.add(jMenuSaveSite5);

		jMenuSaves.add(jMenuSaveSite1);
		jMenuSaves.add(jMenuSaveSite2);
		jMenuSaves.add(jMenuSaveSite3);
		jMenuSaves.add(jMenuSaveSite4);
		jMenuSaves.add(jMenuSaveSite5);

		jMenuGet.add(jMenuGetSite1);
		jMenuGet.add(jMenuGetSite2);
		jMenuGet.add(jMenuGetSite3);
		jMenuGet.add(jMenuGetSite4);
		jMenuGet.add(jMenuGetSite5);

		jMenuGets.add(jMenuGetSite1);
		jMenuGets.add(jMenuGetSite2);
		jMenuGets.add(jMenuGetSite3);
		jMenuGets.add(jMenuGetSite4);
		jMenuGets.add(jMenuGetSite5);

		jMenuBar.add(jMenuFunction);
		jMenuBar.add(jMenuAboutUs);

		jMenuReplay.addActionListener(this);
		jMenuReLogin.addActionListener(this);
		jMenuExit.addActionListener(this);
		jMenuAccount.addActionListener(this);
		jMenuChangeAnimal.addActionListener(this);
		jMenuChangeGirl.addActionListener(this);
		jMenuChangeSport.addActionListener(this);
		jMenuSaveSite1.addActionListener(this);
		jMenuSaveSite2.addActionListener(this);
		jMenuSaveSite3.addActionListener(this);
		jMenuSaveSite4.addActionListener(this);
		jMenuSaveSite5.addActionListener(this);
		jMenuGetSite1.addActionListener(this);
		jMenuGetSite2.addActionListener(this);
		jMenuGetSite3.addActionListener(this);
		jMenuGetSite4.addActionListener(this);
		jMenuGetSite5.addActionListener(this);

		this.setJMenuBar(jMenuBar);
	}

	private void initFrame() {
		this.setTitle("Puzzle Game GameFrame v1.0");
		this.setSize(603, 680);
		this.setAlwaysOnTop(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.addKeyListener(this);
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		if (code == KeyEvent.VK_A) {
			this.getContentPane().removeAll();
			JLabel all = new JLabel(new ImageIcon(imagePath + "all.jpg"));
			all.setBounds(83, 134, 420, 420);
			this.getContentPane().add(all);

			JLabel background = new JLabel(new ImageIcon("image\\background.png"));
			background.setBounds(40, 40, 508, 560);
			this.getContentPane().add(background);

			this.getContentPane().repaint();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (victory())
			return;

		int code = e.getKeyCode();

		switch (code) {
			case KeyEvent.VK_UP -> {
				if (x == 3)
					return;
				data[x][y] = data[x + 1][y];
				data[++x][y] = 0;
				step++;
				initImage();
			}
			case KeyEvent.VK_DOWN -> {
				if (x == 0)
					return;
				data[x][y] = data[x - 1][y];
				data[--x][y] = 0;
				step++;
				initImage();
			}
			case KeyEvent.VK_LEFT -> {
				if (y == 3)
					return;
				data[x][y] = data[x][y + 1];
				data[x][++y] = 0;
				step++;
				initImage();
			}
			case KeyEvent.VK_RIGHT -> {
				if (y == 0)
					return;
				data[x][y] = data[x][y - 1];
				data[x][--y] = 0;
				step++;
				initImage();
			}
			case KeyEvent.VK_A -> initImage();
			case KeyEvent.VK_W -> {
				data = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 0}};
				initImage();
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object object = e.getSource();
		if (object == jMenuReplay) {
			replay();
		} else if (object == jMenuReLogin) {
			this.setVisible(false);
			new LoginFrame();
		} else if (object == jMenuExit) {
			System.exit(0);
		} else if (object == jMenuAccount) {
			account();
		} else if (object == jMenuChangeAnimal) {
			imagePath = "image\\Animal\\Animal" + (random.nextInt(8) + 1) + "\\";
			replay();
		} else if (object == jMenuChangeGirl) {
			imagePath = "image\\Girl\\Girl" + (random.nextInt(11) + 1) + "\\";
			replay();
		} else if (object == jMenuChangeSport) {
			imagePath = "image\\Sport\\Sport" + (random.nextInt(10) + 1) + "\\";
			replay();
		} else if (object == jMenuSaveSite1) {
			save(1);
		} else if (object == jMenuSaveSite2) {
			save(2);
		} else if (object == jMenuSaveSite3) {
			save(3);
		} else if (object == jMenuSaveSite4) {
			save(4);
		} else if (object == jMenuSaveSite5) {
			save(5);
		} else if (object == jMenuGetSite1) {
			get(1);
		} else if (object == jMenuGetSite2) {
			get(2);
		} else if (object == jMenuGetSite3) {
			get(3);
		} else if (object == jMenuGetSite4) {
			get(4);
		} else if (object == jMenuGetSite5) {
			get(5);
		}
	}

	private void save(int site) {
		if (jMenuSavesActive.get(site - 1))
			RegisterFrame.showDialog("该位置已有存档");
		else {
			File saveFile = new File("local//site" + site + "//save.txt");
			BufferedWriter bw;
			try {
				bw = new BufferedWriter(new FileWriter(saveFile));
				bw.write("imagePath=" + imagePath);
				bw.newLine();
				bw.write("step=" + step);
				bw.newLine();
				for (int i = 0; i < 4; i++)
					for (int j = 0; j < 4; j++) {
						bw.write("data[" + i + "][" + j + "]=" + data[i][j]);
						bw.newLine();
					}
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			jMenuSavesActive.set(site - 1, true);
			RegisterFrame.showDialog("存档成功");
			initSaveGet();
		}
	}

	private void get(int site) {
		if (jMenuSavesActive.get(site - 1)) {
			File saveFile = new File("local//site" + site + "//save.txt");
			BufferedReader br;
			try {
				br = new BufferedReader(new FileReader(saveFile));
				imagePath = br.readLine().split("=")[1];
				step = Integer.parseInt(br.readLine().split("=")[1]);
				for (int i = 0; i < 4; i++)
					for (int j = 0; j < 4; j++)
						data[i][j] = Integer.parseInt(br.readLine().split("=")[1]);
				br.close();
				saveFile.delete();
			} catch (IOException e) {
				e.printStackTrace();
			}
			jMenuSavesActive.set(site - 1, false);
			RegisterFrame.showDialog("读取成功");
			initSaveGet();
			initImage();
		} else
			RegisterFrame.showDialog("该位置没有存档");

	}

	private void replay() {
		initData();
		step = 0;
		initImage();
	}

	private static void account() {
		JDialog jDialog = new JDialog();
		JLabel jLabel = new JLabel(new ImageIcon("image\\about.jpg"));
		jLabel.setBounds(0, 0, 640, 640);
		jDialog.getContentPane().add(jLabel);
		jDialog.setSize(640, 640);
		jDialog.setAlwaysOnTop(true);
		jDialog.setLocationRelativeTo(null);
		jDialog.setModal(true);
		jDialog.setVisible(true);
	}
}
