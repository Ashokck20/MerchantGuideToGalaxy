package com.merchant.guide.galaxy.output;

import java.util.Map;

import com.merchant.guide.galaxy.input.InputConstants;

/**
 * @author Ashok 
 * This is used to displays the output data
 */

public class ProcessOutPutData {

	public void displayOutPut(Map<String, String> finalOutPutMap) {

		finalOutPutMap.forEach((k, v) -> {

			StringBuilder result = new StringBuilder();

			if (v == InputConstants.NO_IDEA || Double.valueOf(v).intValue() == -1) {

				result.append(InputConstants.NO_IDEA);

			} else {

				if (k.startsWith(InputConstants.HOW_MUCH_IS)) {

					result.append(k.substring(InputConstants.HOW_MUCH_IS.length(), k.length() - 1).trim());

					result.append(InputConstants.IS);

					result.append(Double.valueOf(v).intValue());

				} else if (k.startsWith(InputConstants.HOW_MANY_CREDITS_IS)) {

					result.append(k.substring(InputConstants.HOW_MANY_CREDITS_IS.length(), k.length() - 1).trim());

					result.append(InputConstants.IS);

					result.append(Double.valueOf(v).intValue());

					result.append(InputConstants.CREDITS);

				} else

					result.append(InputConstants.NO_IDEA);

			}

			System.out.println(result.toString());

		});

	}

}