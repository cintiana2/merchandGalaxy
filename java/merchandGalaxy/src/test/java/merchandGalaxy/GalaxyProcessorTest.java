package merchandGalaxy;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import merchandGalaxy.translator.GalaxyProcessor;

public class GalaxyProcessorTest {

	@Test
	public void isValidEntryTest() {
		StringBuilder sb = new StringBuilder();
		sb.append("glob is I\n");
		sb.append("prok is V\n");
		sb.append("pish is X\n");
		sb.append("tegj is L\n");
		sb.append("glob glob Silver is 34 Credits\n");
		sb.append("glob prok Gold is 57800 Credits\n");
		sb.append("pish pish Iron is 3910 Credits\n");
		sb.append("how much is pish tegj glob glob ?\n");
		sb.append("how many Credits is glob prok Silver ?\n");
		sb.append("how many Credits is glob prok Gold ?\n");
		sb.append("how many Credits is glob prok Iron ?\n");
		sb.append("how much wood could a woodchuck chuck if a woodchuck could chuck wood ?");

		StringBuilder resp = new StringBuilder();
		resp.append("pish tegj glob glob is 42\n");
		resp.append("glob prok Silver is 68 Credits\n");
		resp.append("glob prok Gold is 57800 Credits\n");
		resp.append("glob prok Iron is 782 Credits\n");
		resp.append("I have no idea what you are talking about");

		assertEquals(resp.toString(), new GalaxyProcessor(sb.toString()).answerQuestions());

	}

	@Test
	public void isValidEntryTestWithCurrencyNotExist() {
		StringBuilder sb = new StringBuilder();
		sb.append("glob is I\n");
		sb.append("prok is V\n");
		sb.append("pish is X\n");
		sb.append("tegj is L\n");
		sb.append("glob glob Silver is 34 Credits\n");
		sb.append("glob prok Gold is 57800 Credits\n");
		sb.append("pish pish Cruzeiro Real is 3910 Credits\n");
		sb.append("how much is pish tegj glob glob ?\n");
		sb.append("how many Credits is glob prok Silver ?\n");
		sb.append("how many Credits is glob prok Gold ?\n");
		sb.append("how many Credits is glob prok Iron ?\n");
		sb.append("how much wood could a woodchuck chuck if a woodchuck could chuck wood ?");

		StringBuilder resp = new StringBuilder();
		resp.append("pish tegj glob glob is 42\n");
		resp.append("glob prok Silver is 68 Credits\n");
		resp.append("glob prok Gold is 57800 Credits\n");
		resp.append("I have no idea what you are talking about\n");
		resp.append("I have no idea what you are talking about");

		assertEquals(resp.toString(), new GalaxyProcessor(sb.toString()).answerQuestions());

	}

	@Test
	public void isValidEntryTestWithAnotherCurrency() {
		StringBuilder sb = new StringBuilder();
		sb.append("glob is I\n");
		sb.append("prok is V\n");
		sb.append("pish is X\n");
		sb.append("tegj is L\n");
		sb.append("glob glob Silver is 34 Credits\n");
		sb.append("glob prok Gold is 57800 Credits\n");
		sb.append("pish pish Real is 3910 Credits\n");
		sb.append("how much is pish tegj glob glob ?\n");
		sb.append("how many Credits is glob prok Silver ?\n");
		sb.append("how many Credits is glob prok Gold ?\n");
		sb.append("how many Credits is glob prok Real ?\n");
		sb.append("how much wood could a woodchuck chuck if a woodchuck could chuck wood ?");

		StringBuilder resp = new StringBuilder();
		resp.append("pish tegj glob glob is 42\n");
		resp.append("glob prok Silver is 68 Credits\n");
		resp.append("glob prok Gold is 57800 Credits\n");
		resp.append("glob prok Real is 782 Credits\n");
		resp.append("I have no idea what you are talking about");
		System.out.println(new GalaxyProcessor(sb.toString()).answerQuestions());
		assertEquals(resp.toString(), new GalaxyProcessor(sb.toString()).answerQuestions());

	}

	@Test
	public void isValidEntryTestWithCurrencyAnotherRomanvalue() {
		StringBuilder sb = new StringBuilder();
		sb.append("glob is I\n");
		sb.append("prok is V\n");
		sb.append("pish is X\n");
		sb.append("tegj is M\n");
		sb.append("glob glob Silver is 34 Credits\n");
		sb.append("glob prok Gold is 57800 Credits\n");
		sb.append("pish pish Iron is 3910 Credits\n");
		sb.append("how much is tegj pish glob glob ?\n");
		sb.append("how many Credits is glob prok Silver ?\n");
		sb.append("how many Credits is glob prok Gold ?\n");
		sb.append("how many Credits is glob prok Iron ?\n");
		sb.append("how much wood could a woodchuck chuck if a woodchuck could chuck wood ?");

		StringBuilder resp = new StringBuilder();
		resp.append("tegj pish glob glob is 1012\n");
		resp.append("glob prok Silver is 68 Credits\n");
		resp.append("glob prok Gold is 57800 Credits\n");
		resp.append("glob prok Iron is 782 Credits\n");
		resp.append("I have no idea what you are talking about");
		assertEquals(resp.toString(), new GalaxyProcessor(sb.toString()).answerQuestions());

	}

}
