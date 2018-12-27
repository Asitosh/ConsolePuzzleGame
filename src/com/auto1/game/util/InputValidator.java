package com.auto1.game.util;

import java.util.Scanner;

public class InputValidator {

	

	public static int validateInputIntOption(int numberOfOptions) {
		Scanner in = new Scanner(System.in);
		int userInput = in.nextInt();
		while(!(userInput >= 1 && userInput <= numberOfOptions)) {		
			
			System.out.println("Sorry wrong input please try again");
			userInput = in.nextInt();
		}
		return userInput;
		
	}

}
