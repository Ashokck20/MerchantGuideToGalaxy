package com.merchant.guide.galaxy.roman.validate;

import java.util.HashSet;

import java.util.Set;

import com.merchant.guide.galaxy.input.Roman;

public class Validator {

	private static final Set<Character> threeTimesRepeatedSet = new HashSet<Character>();

	private static final Set<Character> neverSubtractedSet = new HashSet<Character>();

	static {

		threeTimesRepeatedSet.add('i');

		threeTimesRepeatedSet.add('x');

		threeTimesRepeatedSet.add('c');

		threeTimesRepeatedSet.add('m');

		neverSubtractedSet.add('v');

		neverSubtractedSet.add('l');

		neverSubtractedSet.add('d');

		neverSubtractedSet.add('m');

	}

	public static int validateRomanNumber(String romanNumber) {

		char[] charArray = romanNumber.toCharArray();

		char previousChar = ' ';

		int repeatedCount = 1;

		int total = 0;

		int previousCharacterValue = Integer.MAX_VALUE;

		int currentCharacterValue;

		for (int i = 0; i < charArray.length; i++) {

			char currentChar = charArray[i];

			int currentRomanCharNumericValue = Roman.valueOf(String.valueOf(currentChar)).getValue();

			if (previousChar != ' ') {

				previousCharacterValue = Roman.valueOf(String.valueOf(previousChar)).ordinal();

			}

			currentCharacterValue = Roman.valueOf(String.valueOf(currentChar)).ordinal();

			if (currentChar == previousChar && ++repeatedCount < 4 && threeTimesRepeatedSet.contains(currentChar))

				total += currentRomanCharNumericValue;

			else if (repeatedCount > 3)

				total = -1;

			else if (currentChar == previousChar && !threeTimesRepeatedSet.contains(currentChar))

				total = -1;

			else if (previousCharacterValue < currentCharacterValue && !neverSubtractedSet.contains(previousChar)) {

				int previousRomanCharNumericValue = Roman.valueOf(String.valueOf(previousChar)).getValue();

				if (previousCharacterValue + 2 >= currentCharacterValue) {

					total += currentRomanCharNumericValue - (2 * previousRomanCharNumericValue);

					repeatedCount = 1;

				} else

					total = -1;

			} else if (previousCharacterValue < currentCharacterValue && neverSubtractedSet.contains(previousChar))

				total = -1;

			else {

				repeatedCount = 1;

				total += currentRomanCharNumericValue;

			}

			previousChar = currentChar;

			if (total == -1)

				break;

		}

		return total;

	}

}