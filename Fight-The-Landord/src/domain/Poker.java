package domain;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Poker extends JLabel implements MouseListener {

	private String name;

	private boolean side;

	private boolean click = false;

	private boolean clicked = false;

	private static final String imagePath = "image/poker/";

	public Poker(String name, boolean side) {
		this.name = name;
		this.side = side;

		if (this.side) {
			turnFront();
		} else {
			turnBack();
		}

		this.setSize(71, 96);

		this.setVisible(true);

		this.addMouseListener(this);
	}

	public void turnBack() {
		this.setIcon(new ImageIcon(imagePath + "rear.png"));
		this.side = false;
	}

	public void turnFront() {
		this.setIcon(new ImageIcon(imagePath + this.name + ".png"));
		this.side = true;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (click) {
			int step;
			if (clicked)
				step = 20;
			else
				step = -20;
			clicked = !clicked;

			Point from = this.getLocation();
			Point to = new Point(from.x, from.y + step);
			this.setLocation(to);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	public int getColor() {
		return name.charAt(0) - '0';
	}

	public int getValue() {
		int value = Integer.parseInt(name.substring(2));
		int color = getColor();

		if (color == 5)
			value += 100;
		if (value == 1 || value == 2)
			value += 20;

		return value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean getSide() {
		return side;
	}

	public void setSide(boolean side) {
		this.side = side;
	}

	public boolean getClick() {
		return click;
	}

	public void setClick(boolean click) {
		this.click = click;
	}

	public boolean getClicked() {
		return clicked;
	}

	public void setClicked(boolean clicked) {
		this.clicked = clicked;
	}

}