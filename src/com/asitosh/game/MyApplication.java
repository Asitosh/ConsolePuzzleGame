package com.asitosh.game;

import com.asitosh.game.entity.User;
import com.asitosh.game.util.AsciiTextPrinter;
import com.asitosh.game.util.GameStateProcess;
import com.asitosh.game.util.UserProfile;

public class MyApplication {

	public static void main(String[] args) {
		AsciiTextPrinter.print("\t START \t",'#','-');
		UserProfile userProfile = new UserProfile();
		User user = userProfile.checkUser();
		GameStateProcess gameStateProcess = new GameStateProcess();
		gameStateProcess.startResumeGame(user);
		System.out.println("#\t See you again  \t#");
		System.out.println("Keep checking for updates we keep adding new Characters");
		AsciiTextPrinter.print("\t END \t",'#','-');

	}

}
