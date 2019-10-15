package merchandGalaxy.number;

import java.util.Arrays;
import java.util.List;

/**
 * Operations with roman numerals
 * 
 * @author Cintia
 *
 */
public class RomanNumber {

	private static String regexRomanNumber = "^M{0,3}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$";
	private static List<Character> romansCharacters = Arrays
			.asList(new Character[] { 'I', 'V', 'X', 'L', 'C', 'D', 'M' });

	public String value;

	public RomanNumber(String value) {
		this.setValue(value);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public boolean isValid() {
		if (this.getValue() == null || this.getValue().length() == 0) {
			return false;
		}

		return this.getValue().matches(regexRomanNumber);
	}

	public Integer romanToInteger() {
		if (isValid()) {
			int number = 0;
			int lastNumber = 0;
			for (int i = getValue().length() - 1; i >= 0; i--) {
				char character = getValue().charAt(i);

				switch (character) {
				case 'M':
					number = processNumber(1000, lastNumber, number);
					lastNumber = 1000;
					break;

				case 'D':
					number = processNumber(500, lastNumber, number);
					lastNumber = 500;
					break;

				case 'C':
					number = processNumber(100, lastNumber, number);
					lastNumber = 100;
					break;

				case 'L':
					number = processNumber(50, lastNumber, number);
					lastNumber = 50;
					break;

				case 'X':
					number = processNumber(10, lastNumber, number);
					lastNumber = 10;
					break;

				case 'V':
					number = processNumber(5, lastNumber, number);
					lastNumber = 5;
					break;

				case 'I':
					number = processNumber(1, lastNumber, number);
					lastNumber = 1;
					break;
				}
			}
			return number;
		}
		return null;
	}

	private int processNumber(int number, int lastNumber, int beforeNumber) {
		if (lastNumber > number) {
			return beforeNumber - number;
		} else {
			return beforeNumber + number;
		}
	}

	public static boolean isRomanCaracter(Character c) {
		return romansCharacters.contains(c);

	}

}
