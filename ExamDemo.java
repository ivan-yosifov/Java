package com.sirma.exam_problem;

import java.util.Scanner;

public class ExamDemo {

	/**
	 * Main method as starting point of application
	 * @param args Not used
	 */
	public static void main(String[] args) {
		String text = getInput("Please enter some text: ");

		LetterFrequencyCounter counter = new LetterFrequencyCounter(text);

		System.out.println(counter);

	}

	/**
	 * Gets console input from user.
	 * 
	 * @param prompt
	 *            input prompt
	 * @return input The user input
	 */
	public static String getInput(String prompt) {
		Scanner stdIn = new Scanner(System.in);
		System.out.print(prompt);
		String input = stdIn.nextLine();

		return input;
	}

}
