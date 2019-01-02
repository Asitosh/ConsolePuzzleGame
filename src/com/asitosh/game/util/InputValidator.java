package com.asitosh.game.util;

import java.util.Scanner;


/**
 * Class is used to validate user input from scanner
 * @author pathak
 *
 */
public class InputValidator {

	

	/**
	 * This method takes number of options as input and keeps asking for input until
	 * user enters a input between 1 and the number of options.
	 * @param numberOfOptions
	 * @return
	 */
	public static int validateInputIntOption(Scanner in, int numberOfOptions) {
		int userInput = 0;
		try {
			userInput = Integer.parseInt(in.nextLine());
			while(!(userInput >= 1 && userInput <= numberOfOptions)) {		
				
				System.out.println("Sorry wrong input please try again");
				userInput = Integer.parseInt(in.nextLine());
			}
		} catch(NumberFormatException e) {
			userInput = validateInputIntOption(in, numberOfOptions);
		}
		
		return userInput;
		
	}

}
