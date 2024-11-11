package game;

import domain.Poker;

import java.awt.*;
import java.util.ArrayList;

public class Common {
	public static void move(Poker poker, Point from, Point to) {
		if (to.x != from.x) {
			double k = (1.0) * (to.y - from.y) / (to.x - from.x);
			double b = to.y - to.x * k;
			int flag;
			if (from.x < to.x)
				flag = 20;
			else
				flag = -20;
			for (int i = from.x; Math.abs(i - to.x) > 20; i += flag) {
				double y = k * i + b;
				poker.setLocation(i, (int) y);
				try {
					Thread.sleep(5);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}
		}
		poker.setLocation(to);
	}

	public static void rePosition(GameFrame frame, ArrayList<Poker> pokers, int flag) {
		Point point = new Point();
		if (flag == 0) {
			point.x = 50;
			point.y = (450 / 2) - (pokers.size() + 1) * 15 / 2;
		} else if (flag == 1) {
			point.x = (800 / 2) - (pokers.size() + 1) * 21 / 2;
			point.y = 450;
		} else if (flag == 2) {
			point.x = 700;
			point.y = (450 / 2) - (pokers.size() + 1) * 15 / 2;
		}
		int len = pokers.size();
		for (Poker poker : pokers) {
			move(poker, poker.getLocation(), point);
			GameFrame.container.setComponentZOrder(poker, 0);
			if (flag == 1)
				point.x += 21;
			else
				point.y += 15;
		}
	}
}
