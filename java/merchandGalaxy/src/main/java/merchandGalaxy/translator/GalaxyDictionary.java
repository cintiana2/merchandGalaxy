package merchandGalaxy.translator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import merchandGalaxy.number.RomanNumber;

/**
 * Create dictionary to translate messages, and calculate values
 * 
 * @author Cintia
 *
 */
public class GalaxyDictionary {
	private String CREDITS = "Credits";
	private String EQUALS = " is ";
	/**
	 * Dictionary to romanNumbers or others
	 */
	private HashMap<String, Object> dictionary;

	/**
	 * Entry is text without questions lines
	 * 
	 * @param text
	 */
	public GalaxyDictionary(String text) {
		if (text != null) {
			dictionary = new HashMap<String, Object>();
			loadDictionary(text);
		}
	}

	public HashMap<String, Object> getDictionary() {
		return dictionary;
	}

	public void setDictionary(HashMap<String, Object> dictionary) {
		this.dictionary = dictionary;
	}
	
	private void loadDictionary(String text) {
		String lines[] = text.split("\n");
		List<String> listWithCredits = new ArrayList<String>();
		processLines(lines, listWithCredits);
		
		for (String line : listWithCredits) {
			String[] words = line.split(" ");
			processLineWithCredits(words);

		}
	}
	
	private void processLines(String lines[], List<String> listWithCredits) {
		for (String line : lines) {
			if (line.trim().endsWith(CREDITS)) {
				listWithCredits.add(line);
			} else {
				String[] wordValue = line.split(EQUALS);
				if (wordValue.length == 2) {
					String word = wordValue[0].trim();
					String value = wordValue[1].trim();
					if (RomanNumber.isRomanCaracter(value.charAt(0))) {
						dictionary.put(word, value.charAt(0));
					}
				}
			}
		}
	}
	
	private void processLineWithCredits(String[] words) {
		String romanNumber = "";
		String newWord = "";
		for (int i = 0; i < words.length; ++i) {
			String word = words[i].trim();
			if (word.trim().equals(CREDITS)) {
				break;
			}

			if (word.trim().equals("is") && i > 0 && i < words.length - 1) {
				String value = words[i + 1].trim();
				if (isNumeric(value)) {
					Double total = new Double(value);
					Integer totalRoman = new RomanNumber(romanNumber).romanToInteger();

					if (romanNumber != null) {
						if (totalRoman != null) {
							total = total / totalRoman;
						} else {
							break;// invalid entry roman number invalid next entry
						}
					}
					dictionary.put(newWord, total);
				}
				break;
			}

			if (dictionary.containsKey(word)) {
				romanNumber = romanNumber + dictionary.get(word).toString();

			} else {
				if (newWord.length() > 0 && i > 0) {
					String beforeWord = words[i - 1].trim();
					if (!dictionary.containsKey(beforeWord)) {
						newWord = newWord + " " + word;
					} else {
						break; // invalid entry
					}
				} else {
					newWord = word;
				}

			}
		}
		
	}	

	public Double calculateTotal(String value) {
		String romanValue = "";
		Double mult = 1.0;
		String[] words = value.trim().split(" ");
		Double total = null;
		for (int i = 0; i < words.length; ++i) {
			String word = words[i];
			if (dictionary.containsKey(word)) {
				Object number = dictionary.get(word);
				if (number instanceof Double && i == (words.length - 1)) {
					mult = (Double) number;
				} else if (number instanceof Double) {
					romanValue = "";
					break; // invalid entry
				} else {
					romanValue = romanValue + number;
				}
			} else {
				romanValue = "";
				break; // invalid entry
			}

		}
		if (romanValue != "") {
			Integer totalRoman = new RomanNumber(romanValue).romanToInteger();
			if (totalRoman != null) {
				total = totalRoman * mult;
			}
		}
		return total;
	}

	private static boolean isNumeric(String str) {
		return str.matches("\\d+(\\.\\d+)?");
	}

}
