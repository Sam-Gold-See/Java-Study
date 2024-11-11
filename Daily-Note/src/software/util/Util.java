package software.util;

import software.note.Note;

import javax.swing.*;
import java.io.*;
import java.util.LinkedList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class Util {
	private static final File localPath = new File("local");

	private static final File localFile = new File(localPath, "saves.txt");

	private static final File desktopPath = new File(System.getProperty("user.home"), "Desktop");

	private static final File desktopFile = new File(desktopPath, "local.zip");

	public static void showString(String content) {
		//创建一个弹框对象
		JDialog jDialog = new JDialog();
		//给弹框设置大小
		jDialog.setSize(200, 150);
		//让弹框置顶
		jDialog.setAlwaysOnTop(true);
		//让弹框居中
		jDialog.setLocationRelativeTo(null);
		//弹框不关闭永远无法操作下面的界面
		jDialog.setModal(true);

		//创建JLabel对象管理文字并添加到弹框当中
		JLabel warning = new JLabel(content);
		warning.setBounds(0, 0, 200, 150);
		jDialog.getContentPane().add(warning);

		//让弹框展示出来
		jDialog.setVisible(true);
	}

	public static LinkedList<Note> initData() {
		LinkedList<Note> notes = new LinkedList<>();
		if (!localPath.exists())
			localPath.mkdir();
		BufferedReader br;
		try {
			if (!localFile.exists())
				localFile.createNewFile();
			br = new BufferedReader(new FileReader(localFile));
			String line;
			while ((line = br.readLine()) != null)
				notes.add(new Note(line));
			br.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		System.out.println(notes);
		return notes;
	}

	public static void addData(Note note) {
		BufferedWriter bw;
		try {
			bw = new BufferedWriter(new FileWriter(localFile, true));
			bw.write(note.toString());
			bw.newLine();
			bw.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public static void updateData(LinkedList<Note> notes) {
		BufferedWriter bw;
		try {
			bw = new BufferedWriter(new FileWriter(localFile));
			for (Note note : notes) {
				bw.write(note.toString());
				bw.newLine();
			}
			bw.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public static void zipOutputData() {
		ZipOutputStream zos;
		try {
			zos = new ZipOutputStream(new FileOutputStream(desktopFile));
			compress(localPath, "", zos);
			zos.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	private static void compress(File folder, String path, ZipOutputStream zos) throws IOException {
		File[] files = folder.listFiles();
		if (files != null)
			for (File file : files)
				if (file.isDirectory())
					compress(file, path + file.getName() + File.separator, zos);
				else {
					zos.putNextEntry(new ZipEntry(path + file.getName()));
					FileInputStream fis;
					try {
						fis = new FileInputStream(file);
						byte[] buffer = new byte[5 * 1024 * 1024];
						int length;
						while ((length = fis.read(buffer)) > 0)
							zos.write(buffer, 0, length);
					} catch (IOException ioe) {
						ioe.printStackTrace();
					}
					zos.closeEntry();
				}
	}

	public static void zipInputData() {
		ZipInputStream zis;
		try{
			zis = new ZipInputStream(new FileInputStream(desktopFile));
			ZipEntry zipEntry = zis.getNextEntry();
			while (zipEntry != null) {
				String filePath = localPath + File.separator + zipEntry.getName();
				if(!zipEntry.isDirectory()) {
					extract(zis,filePath);
				}else{
					File newDir = new File(filePath);
					newDir.mkdirs();
				}
				zis.closeEntry();
				zipEntry = zis.getNextEntry();
			}
		}catch (IOException ioe){
			ioe.printStackTrace();
		}
	}

	private static void extract(ZipInputStream zis, String path) throws IOException {
		FileOutputStream fos;
		try{
			fos = new FileOutputStream(path);
			byte[] buffer = new byte[5 * 1024 * 1024];
			int length;
			while ((length = zis.read(buffer)) >0)
				fos.write(buffer, 0, length);
			fos.close();
		}catch (IOException ioe){
			ioe.printStackTrace();
		}
	}
}
