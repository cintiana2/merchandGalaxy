package merchandGalaxy;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import merchandGalaxy.translator.GalaxyDictionary;

public class GalaxyDictionaryTest {

	@Test
	public void isValidEntry() {
		StringBuilder sb = new StringBuilder();
		sb.append("glob is I\n");
		sb.append("prok is V\n");
		sb.append("pish is X\n");
		sb.append("tegj is L\n");
		sb.append("glob glob Silver is 34 Credits\n");
		sb.append("glob prok Gold is 57800 Credits\n");
		sb.append("pish pish Iron is 3910 Credits\n");

		GalaxyDictionary dic = new GalaxyDictionary(sb.toString());
		assertEquals(dic.getDictionary().size(), 7);
	}

	@Test
	public void hasOneInValidEntry() {
		StringBuilder sb = new StringBuilder();
		sb.append("glob is I\n");
		sb.append("prok is V\n");
		sb.append("pish is X\n");
		sb.append("tegj is L\n");
		sb.append("cintia glob Silver is 34 Credits\n");
		sb.append("glob prok Gold is 57800 Credits\n");
		sb.append("pish pish Iron is 3910 Credits\n");

		GalaxyDictionary dic = new GalaxyDictionary(sb.toString());
		assertEquals(dic.getDictionary().size(), 6);
	}

	@Test
	public void hasOneRomanNumberInvalidEntry() {
		StringBuilder sb = new StringBuilder();
		sb.append("glob is I\n");
		sb.append("prok is V\n");
		sb.append("pish is X\n");
		sb.append("tegj is L\n");
		sb.append("pish pish pish pish Silver is 34 Credits\n");
		sb.append("glob prok Gold is 57800 Credits\n");
		sb.append("pish pish Iron is 3910 Credits\n");

		GalaxyDictionary dic = new GalaxyDictionary(sb.toString());
		assertEquals(dic.getDictionary().size(), 6);
	}

	@Test
	public void hasOneCurrencyNewDescription() {
		StringBuilder sb = new StringBuilder();
		sb.append("glob is I\n");
		sb.append("prok is V\n");
		sb.append("pish is X\n");
		sb.append("tegj is L\n");
		sb.append("pish pish pish cintia is 34 Credits\n");
		sb.append("glob prok Gold is 57800 Credits\n");
		sb.append("pish pish Iron is 3910 Credits\n");

		GalaxyDictionary dic = new GalaxyDictionary(sb.toString());
		assertEquals(dic.getDictionary().size(), 7);
	}

}
