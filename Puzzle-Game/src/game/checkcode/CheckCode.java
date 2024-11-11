package game.checkcode;

import java.util.Random;
import java.util.ArrayList;

public class CheckCode {
	public static String getCode(){
		ArrayList<Character> list = new ArrayList<>();
		for(int i=0;i<26;i++){
			list.add((char)('a'+i));
			list.add((char)('A'+i));
		}

		StringBuilder result = new StringBuilder();
		Random random = new Random();
		for(int i=0;i<4;i++){
			int randomIndex = random.nextInt(list.size());
			char c = list.get(randomIndex);
			result.append(c);
		}

		int number = random.nextInt(10);

		result.append(number);

		char[] chars = result.toString().toCharArray();

		int index = random.nextInt(chars.length);

		char temp = chars[index];
		chars[index] = chars[4];
		chars[4] = temp;

		return new String(chars);
	}
}
