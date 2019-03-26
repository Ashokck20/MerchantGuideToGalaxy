package com.merchant.guide.galaxy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import com.merchant.guide.galaxy.input.ProcessInputData;
import com.merchant.guide.galaxy.output.ProcessOutPutData;

/**
 * 
 * @author Ashok
 *  This is the main program and it requires input.txt file to parse and
 *  displays the output.
 * 
 */

public class MerchantsGuideToGalaxy {
	public static void main(String[] args) {
		String inputFile = "D:\\input.txt";
		if (args.length > 0)
			inputFile = args[0];

		BufferedReader reader = null;

		try {
			ProcessInputData inputData = new ProcessInputData();
			reader = new BufferedReader(new FileReader(inputFile));
			String inputLine = null;
			while ((inputLine = reader.readLine()) != null) {
				inputData.processData(inputLine.toLowerCase());
			}

			ProcessOutPutData outputData = new ProcessOutPutData();
			outputData.displayOutPut(inputData.getFinalOutPutMap());
		} catch (IOException e) {
			System.out.println("Error while reading the file " + e);
		} finally {
			if (reader != null)
				try {
					reader.close();
				} catch (IOException e) {
					System.out.println("Error while closing Buffered Reader " + e);
				}
		}
	}
}