package com.sirma.exam_problem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Programming problem from Sirma.com
 * LetterFrequencyCounter.java
 * Purpose: Counts the number of occurrences of the letters in a given text.
 * 			Prints a statistics report of the 20 most frequented letters.
 *
 * @author Ivan Yosifov
 * @version 1.0 26/11/14
 */
public class LetterFrequencyCounter {
	public static final int BAR_LIMIT = 20;
	public static final int LETTER_LIMIT = 20;
	public static final char BAR_SYMBOL = '#';
	public static final char PADDING_SYMBOL = ' ';
	public static final char NEW_LINE = '\n';

	private String text;
	private List<Character> letters;
	private Map<Character, Integer> unsortedLetters;
	private Map<Character, Integer> sortedLetters;
	private int maxValue; // number of occurrences of the most frequented letter
	private int digitsOfMaxValue;

	/**
	 * Default no-arguments constructor
	 */
	public LetterFrequencyCounter() {
		this("");
	}

	/**
	 * Constructor that accepts a string of text
	 * 
	 * @param text
	 *            The text
	 */
	public LetterFrequencyCounter(String text) {
		setText(text);
	}

	/**
	 * Get the text
	 * 
	 * @return The text
	 */
	public String getText() {
		return text;
	}

	/**
	 * The letters are extracted, sorted and assigned to a map. The max value of
	 * the most frequented letter is set.
	 * 
	 * @param text
	 */
	public void setText(String text) {
		this.text = text;
		extractLetters(text);
		sortLetters(unsortedLetters);
		setMaxValue();
	}

	/**
	 * Returns a new map of sorted letters.
	 * 
	 * @return The map of sorted letters
	 */
	public Map<Character, Integer> getSortedLetters() {
		return new LinkedHashMap<Character, Integer>(sortedLetters);
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append(NEW_LINE);
		result.append("Most common letters:");
		result.append(NEW_LINE);

		Iterator<Map.Entry<Character, Integer>> iterator = sortedLetters
				.entrySet().iterator();
		int count = 0;
		while (iterator.hasNext() && count < LETTER_LIMIT) {
			Map.Entry<Character, Integer> pairs = iterator.next();
			char key = pairs.getKey();
			int value = pairs.getValue();
			String padding = getPadding(value);
			int barLength = calculateBarLength(getMaxValue(), value);
			String bar = repeat(barLength, BAR_SYMBOL);
			result.append(NEW_LINE);
			result.append(key + ": " + padding + value + " " + bar);
			count++;
		}

		return result.toString();
	}

	/**
	 * Extracts all upper-case and lower-case letters from a string of text, and
	 * assigns them to a list. The list is converted to a key-sorted map.
	 * 
	 * @param text
	 *            The text
	 */
	private void extractLetters(String text) {
		letters = new ArrayList<>();
		for (int i = 0; i < text.length(); i++) {
			char symbol = text.charAt(i);

			if (Character.isLetter(symbol)) {
				symbol = Character.toUpperCase(symbol);
				letters.add(symbol);
			}
		}

		Collections.sort(letters);
		Collections.reverse(letters);

		convertToMap(letters);
	}

	/**
	 * Converts a list of letters to a key-sorted map
	 * 
	 * @param letters
	 *            List of letters
	 */
	private void convertToMap(List<Character> letters) {
		unsortedLetters = new LinkedHashMap<>();

		for (int i = 0; i < letters.size(); i++) {
			Character letter = letters.get(i);
			Integer counter = unsortedLetters.get(letter);
			if (counter == null) {
				counter = 1;
			} else {
				counter += 1;
			}

			unsortedLetters.put(letter, counter);
		}
	}

	/**
	 * Converts a key-sorted map to a value-sorted map
	 * 
	 * @param letterFrequencyMap
	 *            The map of unsorted letters
	 */
	private void sortLetters(Map<Character, Integer> letterFrequencyMap) {
		ValueComparator bvc = new ValueComparator(letterFrequencyMap);
		sortedLetters = new TreeMap<>(bvc);
		sortedLetters.putAll(letterFrequencyMap);
	}

	/**
	 * Find out the max value of the most frequented letter in the sorted map.
	 * If the sorted map is empty, max value is set to 0.
	 */
	private void setMaxValue() {
		if (sortedLetters.isEmpty()) {
			maxValue = 0;
		} else {
			// most frequented letter is always the first
			maxValue = (int) sortedLetters.values().toArray()[0];
			digitsOfMaxValue = countDigits(maxValue);			
		}
	}

	/**
	 * Get value for maxValue
	 * 
	 * @return maxValue
	 */
	private int getMaxValue() {
		return maxValue;
	}

	/**
	 * Get total number of digits in maxValue
	 * 
	 * @return total Number of digits in maxValue
	 */
	private int getDigitsOfMaxValue() {
		return digitsOfMaxValue;
	}

	/**
	 * Calculates padding for a number
	 * 
	 * @param value
	 *            The value to be calculated
	 * @return String representation of a padding
	 */
	private String getPadding(int value) {
		String result = "";
		int digitsValue = countDigits(value);
		int difference = getDigitsOfMaxValue() - digitsValue;

		if (difference > 0) {
			result = repeat(difference, PADDING_SYMBOL);
		}

		return result;
	}

	/**
	 * Creates a repeated String of a given symbol
	 * 
	 * @param times
	 *            How many times to repeat the symbol
	 * @param symbol
	 *            The symbol used for the repeated string
	 * @return the combined string
	 */
	private static String repeat(int times, char symbol) {
		StringBuilder result = new StringBuilder(times);
		for (int i = 0; i < times; i++) {
			result.append(symbol);
		}

		return result.toString();
	}

	/**
	 * Counts the number of digits in a given number
	 * 
	 * @param number
	 *            number whose digits will be counted
	 * @return total number of digits in a given number
	 */
	private static int countDigits(int number) {
		int numberOfDigits = 0;
		while (number > 0) {
			number /= 10;
			numberOfDigits++;
		}

		return numberOfDigits;
	}

	/**
	 * Calculates length of statistics bar
	 * 
	 * @param maxValue value of most frequented letter
	 * @param value value of current letter
	 * @return length of statistics bar
	 */
	private int calculateBarLength(int maxValue, int value){
		int length = (int) Math.round((double) BAR_LIMIT / maxValue * value);
		
		return length;
	}
}
