package com.sirma.exam_problem;

import java.util.Comparator;
import java.util.Map;

/**
 * Programming problem from Sirma.com ValueComparator.java 
 * Purpose: Compares and sorts a map based on its values
 * 
 * Note: copied from http://stackoverflow.com/questions/109383/how-to-sort-a-mapkey-value-on-the-values-in-java
 *
 * @author Ivan Yosifov
 * @version 1.0 26/11/14
 */
public class ValueComparator implements Comparator<Character> {
	Map<Character, Integer> base;

	/**
	 * Constructor that accepts a map
	 * 
	 * @param base
	 *            the map
	 */
	public ValueComparator(Map<Character, Integer> base) {
		this.base = base;
	}

	/**
	 * Compares two Character values
	 */
	public int compare(Character a, Character b) {
		if (base.get(a) >= base.get(b)) {
			return -1;
		} else {
			return 1;
		}
	}
}
