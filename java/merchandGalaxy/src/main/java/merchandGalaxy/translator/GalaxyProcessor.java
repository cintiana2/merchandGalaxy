package merchandGalaxy.translator;

import java.util.ArrayList;
import java.util.List;

/**
 * Processor and translate questions
 * 
 * @author Cintia
 *
 */
public class GalaxyProcessor {

	private static String QUESTION = "?";
	private static String HOW_MUCH = "how much is";
	private static String HOW_MANY = "how many Credits is";
	private static String NO_IDEA = "I have no idea what you are talking about";
	private GalaxyDictionary dictionary;
	private List<String> questions;

	public GalaxyProcessor(String lines) {
		load(lines);
	}

	private void load(String lines) {
		if (lines != null && lines.length() > 0) {
			StringBuilder entryDictionary = new StringBuilder();
			questions = new ArrayList<String>();
			for (String line : lines.split("\n")) {
				if (line.trim().endsWith(QUESTION)) {
					questions.add(line);
				} else {
					entryDictionary.append(line + "\n");
				}
			}
			dictionary = new GalaxyDictionary(entryDictionary.toString());
		}

	}

	public String answerQuestions() {
		if (questions != null) {
			StringBuilder resp = new StringBuilder();
			for (String question : questions) {
				if (question.trim().startsWith(HOW_MUCH)) {
					resp.append(answerHowMuch(question) + "\n");
				} else if (question.trim().startsWith(HOW_MANY)) {
					resp.append(answerHowMany(question) + "\n");
				} else {
					resp.append(NO_IDEA + "\n");
				}
			}
			String response = resp.toString();
			if (response.length() > 0) {
				response = response.substring(0, response.length() - 1);
			}
			return response;
		}

		return "";
	}

	private String answerHowMuch(String line) {

		String number = processString(line, HOW_MUCH);
		if (number != null && number.length() > 0) {
			Double total = dictionary.calculateTotal(number);
			if (total != null) {
				return number + " is " + total.intValue();
			}

		}

		return NO_IDEA;
	}

	private String answerHowMany(String line) {

		String number = processString(line, HOW_MANY);
		if (number != null && number.length() > 0) {
			Double total = dictionary.calculateTotal(number);
			if (total != null) {
				return number + " is " + total.intValue() + " Credits";
			}

		}

		return NO_IDEA;
	}

	private String processString(String line, String how) {
		String question = line.trim();
		question = question.replace(QUESTION, "");
		question = question.replace(how, "");
		question = question.trim();
		return question;
	}

}
