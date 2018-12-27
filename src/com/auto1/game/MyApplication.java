package com.auto1.game;

import com.auto1.game.entity.User;
import com.auto1.game.util.GameStateProcess;
import com.auto1.game.util.UserProfile;

public class MyApplication {

	public static void main(String[] args) {
		UserProfile userProfile = new UserProfile();
		User user = userProfile.checkUser();
		GameStateProcess gameStateProcess = new GameStateProcess();
		gameStateProcess.startResumeGame(user);
		System.out.println("#\t See you again  \t#");
		System.out.println("Keep checking for updates we keep adding new Characters");

	}

}
