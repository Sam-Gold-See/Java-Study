package util;

import java.util.Random;
import java.util.ArrayList;

public class Code {
	public static String getCode() {
		ArrayList<Character> list = new ArrayList<>();
		for (int i = 0; i < 26; i++) {
			list.add((char) ('a' + i));
			list.add((char) ('A' + i));
		}

		int randomIndex;

		StringBuilder result = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 4; i++) {
			randomIndex = random.nextInt(list.size());
			result.append(list.get(randomIndex));
		}

		int number = random.nextInt(10);

		result.append(number);

		char[] chars = result.toString().toCharArray();

		randomIndex = random.nextInt(5);
		chars[4] = chars[randomIndex];
		chars[randomIndex] = (char) ('0' + number);

		return new String(chars);
	}
}
