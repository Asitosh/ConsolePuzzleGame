package com.asitosh.game.util;

import java.util.Scanner;

import com.asitosh.game.GameFactory;
import com.asitosh.game.dao.GameStateDao;
import com.asitosh.game.dao.GameStateDaoImpl;
import com.asitosh.game.entity.SavedGame;
import com.asitosh.game.entity.User;

public class GameStateProcess {
	
	private GameStateDao gameStateDao = new GameStateDaoImpl();

	

	public void startResumeGame(User user) {
		SavedGame savedGame = gameStateDao.findSavedGame(user.getUserId());
		if (null != savedGame) {
			gameStateDao.deleteSavedGame(user.getUserId());
			System.out.println("Hey you have paused game would you like to restart");
			System.out.println("Press 1 to resume the game or 2 to start a new game");
			Scanner in = new Scanner(System.in);
			int userInput = InputValidator.validateInputIntOption(in, 2);
			if (1 == userInput) {				
				GameFactory.resumeGame(user, savedGame);
				return;
			}
		}
		GameFactory.startGame(user);
	}
	
	

}
