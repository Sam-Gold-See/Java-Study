package software.ui;

import software.note.Note;
import software.util.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class NoteJFrame extends JFrame implements ActionListener {

	LinkedList<Note> localNotes;
	int localNotesLength;

	//创建三个按钮
	JButton add = new JButton("添加");
	JButton update = new JButton("修改");
	JButton delete = new JButton("删除");

	//创建表格组件
	JTable table;

	//创建菜单的导入和导出
	JMenuItem exportItem = new JMenuItem("导出");
	JMenuItem importItem = new JMenuItem("导入");

	public NoteJFrame() {
		//初始化界面
		initFrame();
		//初始化菜单
		initJMenuBar();
		//初始化数据
		localNotes = Util.initData();
		localNotesLength = localNotes.size();
		//初始化组件
		initView();
		//让界面显示出来
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//获取当前那个组件被点击
		Object obj = e.getSource();
		if (obj == add) {
			this.setVisible(false);
			new AddJFrame();
		} else if (obj == update) {
			//获取用户选择了表格中的哪一行
			//index = 0: 表示用户选择了第一行
			//index = 1: 表示用户选择了第一行
			//i有两个作用：
			//i小于0，表示用户没有选择，此时无法修改
			//i大于等于0：通过这个行数就可以获取二维数组中对应的数据
			int index = table.getSelectedRow();
			if (index < 0)
				Util.showString("未选中要修改的行，请重试");
			else {
				this.setVisible(false);
				new UpdateJFrame(index);
			}

		} else if (obj == delete) {
			//逻辑：
			//1.先判断用户有没有选择表格中的数据
			//2.如果没有选择，弹框提示：未选择。此时提示的弹框用showJDialog方法即可
			//3.如果选择了，弹框提示：是否确定删除。此时提示的弹框用showChooseJDialog方法


			//作用：展示一个带有确定和取消按钮的弹框
			//方法的返回值：0 表示用户点击了确定
			//             1 表示用户点击了取消
			//该弹框用于用户删除时候，询问用户是否确定删除
			int index = table.getSelectedRow();
			if (index < 0)
				Util.showString("未选中要删除的行，请重试");
			else {
				int flag = showChooseJDialog();
				if (flag == 0) {
					localNotes.remove(index);
					Util.updateData(localNotes);
					Util.showString("删除成功");
					this.dispose();
					new NoteJFrame();
				}
			}

		} else if (obj == exportItem) {
			Util.zipOutputData();
			Util.showString("将数据导出到桌面成功");
		} else if (obj == importItem) {
			Util.zipInputData();
			Util.showString("将数据导入到软件成功");
			this.dispose();
			new NoteJFrame();
		}
	}

	//初始化组件
	private void initView() {
		//定义最上面的每日一记
		JLabel title = new JLabel("每日一记");
		title.setBounds(220, 20, 584, 50);
		title.setFont(new Font("宋体", Font.BOLD, 32));
		this.getContentPane().add(title);

		//定义表格的标题
		Object[] tableTitles = {"编号", "标题", "正文"};
		//定义表格的内容
		//二维数组中的每一个一维数组，是表格里面的一行数据
		Object[][] tableData = new Object[localNotesLength][tableTitles.length];
		for (int i = 0; i < localNotes.size(); i++) {
			tableData[i][0] = i + 1;
			tableData[i][1] = localNotes.get(i).getTitle();
			tableData[i][2] = localNotes.get(i).getContent();
		}
		//定义表格组件
		//并给表格设置标题和内容
		table = new JTable(tableData, tableTitles);
		table.setBounds(40, 70, 504, 380);

		//创建可滚动的组件，并把表格组件放在滚动组件中间
		//好处：如果表格中数据过多，可以进行上下滚动
		JScrollPane jScrollPane = new JScrollPane(table);
		jScrollPane.setBounds(40, 70, 504, 380);
		this.getContentPane().add(jScrollPane);

		//给三个按钮设置宽高属性，并添加点击事件
		add.setBounds(40, 466, 140, 40);
		update.setBounds(222, 466, 140, 40);
		delete.setBounds(404, 466, 140, 40);

		add.setFont(new Font("宋体", Font.PLAIN, 24));
		update.setFont(new Font("宋体", Font.PLAIN, 24));
		delete.setFont(new Font("宋体", Font.PLAIN, 24));

		add.addActionListener(this);
		update.addActionListener(this);
		delete.addActionListener(this);


		this.getContentPane().add(add);
		this.getContentPane().add(update);
		this.getContentPane().add(delete);
	}

	//初始化菜单
	private void initJMenuBar() {
		//创建整个的菜单对象
		JMenuBar jMenuBar = new JMenuBar();
		//创建菜单上面的两个选项的对象 （功能  关于我们）
		JMenu functionJMenu = new JMenu("功能");

		//把5个存档，添加到saveJMenu中
		functionJMenu.add(exportItem);
		functionJMenu.add(importItem);

		//将菜单里面的两个选项添加到菜单当中
		jMenuBar.add(functionJMenu);

		//绑定点击事件
		exportItem.addActionListener(this);
		importItem.addActionListener(this);

		//给菜单设置颜色
		jMenuBar.setBackground(new Color(230, 230, 230));

		//给整个界面设置菜单
		this.setJMenuBar(jMenuBar);
	}

	//初始化界面
	private void initFrame() {
		//设置界面的宽高
		this.setSize(600, 600);
		//设置界面的标题
		this.setTitle("每日一记");
		//设置界面置顶
		this.setAlwaysOnTop(true);
		//设置界面居中
		this.setLocationRelativeTo(null);
		//设置关闭模式
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		//取消默认的居中放置，只有取消了才会按照XY轴的形式添加组件
		this.setLayout(null);
		//设置背景颜色
		this.getContentPane().setBackground(new Color(212, 212, 212));
	}

	/*
	 *  作用：展示一个带有确定和取消按钮的弹框
	 *
	 *  方法的返回值：
	 *       0 表示用户点击了确定
	 *       1 表示用户点击了取消
	 *       该弹框用于用户删除时候，询问用户是否确定删除
	 * */
	public int showChooseJDialog() {
		return JOptionPane.showConfirmDialog(this, "是否删除选中数据？", "删除信息确认", JOptionPane.YES_NO_OPTION);
	}
}
