package com.merchant.guide.galaxy.input;

import java.util.Arrays;

import java.util.HashMap;

import java.util.Map;

import com.merchant.guide.galaxy.roman.validate.Validator;

/**
 *
 * @author Ashok
 * This is used to parse each line and process the data after applying some minor calculations.
 * 
 */

public class ProcessInputData {

	Map<String, Roman> romanNumberMap = new HashMap<>(); // this is used to store glob,prok e.t.c
	Map<String, Double> perUnitValueMap = new HashMap<>(); // This is used to store Silver,gold, iron e.t.c.
	Map<String, String> finalOutPutMap = new HashMap<>(); // this is for final out put

	public void processData(String inputLine) {

		String[] dataInputs = inputLine.split("\\s+");

		if (inputLine.startsWith(InputConstants.HOW_MANY_CREDITS_IS)) {

			String key = inputLine;

			String value = String.valueOf(getValue(Arrays.copyOfRange(dataInputs, 4, dataInputs.length - 1)));

			finalOutPutMap.put(key, value);

		} else if (inputLine.startsWith(InputConstants.HOW_MUCH_IS)) {

			String key = inputLine;

			String value = String.valueOf(romanToNumberic(Arrays.copyOfRange(dataInputs, 3, dataInputs.length - 1)));

			finalOutPutMap.put(key, value);

		} else if (inputLine.contains(InputConstants.IS) && !inputLine.contains(InputConstants.CREDITS)) {

			romanNumberMap.put(dataInputs[0], Roman.valueOf(dataInputs[2]));

		} else if (inputLine.contains(InputConstants.IS) && inputLine.contains(InputConstants.CREDITS)) {

			StringBuilder romanNumeral = new StringBuilder();

			int i;

			for (i = 0; i < dataInputs.length; i++) {

				Roman roman = romanNumberMap.get(dataInputs[i]);

				if (roman != null) {

					romanNumeral.append(roman.getSymbol());

				} else

					break;

			}

			int value = Validator.validateRomanNumber(romanNumeral.toString());

			String objectName = dataInputs[i];

			int credit = Integer.parseInt(dataInputs[i + 2]);

			perUnitValueMap.put(objectName, (double) credit / value);

		} else

			finalOutPutMap.put(inputLine, InputConstants.NO_IDEA);

	}

	private int romanToNumberic(String[] arr) {

		if (arr.length == 0)

			return -1;

		StringBuilder romanNumStrBuilder = new StringBuilder();

		int i;

		for (i = 0; i < arr.length; i++) {

			Roman roman = romanNumberMap.get(arr[i]);

			if (roman != null) {

				romanNumStrBuilder.append(roman.getSymbol());

			} else

				break;

		}

		return Validator.validateRomanNumber(romanNumStrBuilder.toString());

	}

	private Double getValue(String[] arr) {

		int currentValue = romanToNumberic(Arrays.copyOfRange(arr, 0, arr.length - 1));

		if (currentValue == -1)

			return (double) -1;

		return currentValue * perUnitValueMap.get(arr[arr.length - 1]);

	}

	public Map<String, String> getFinalOutPutMap() {

		return finalOutPutMap;

	}

}