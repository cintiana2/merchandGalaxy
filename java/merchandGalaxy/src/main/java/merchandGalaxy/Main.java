package merchandGalaxy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

import merchandGalaxy.translator.GalaxyProcessor;

public class Main {

	private static final Logger LOG = Logger.getLogger(Main.class.getName());

	public static void main(String[] args) {
		try {
			String input = readInput();
			System.out.println("INPUT:");
			System.out.println(input);
			System.out.println("\nOUTPUT:");
			System.out.println(new GalaxyProcessor(input).answerQuestions());
		} catch (IOException e) {
			LOG.log(Level.SEVERE, "Error load input file", e);
		}

	}

	private static String readInput() throws IOException {
		ClassLoader classloader = Main.class.getClassLoader();
		InputStream inputStream = classloader.getResourceAsStream("input.txt");
		InputStreamReader streamReader = new InputStreamReader(inputStream);
		BufferedReader reader = new BufferedReader(streamReader);
		StringBuilder fileData = new StringBuilder();
		char[] buf = new char[1024];
		int numRead = 0;
		while ((numRead = reader.read(buf)) != -1) {
			String readData = String.valueOf(buf, 0, numRead);
			fileData.append(readData);
			buf = new char[1024];
		}

		reader.close();

		return fileData.toString();

	}

}
