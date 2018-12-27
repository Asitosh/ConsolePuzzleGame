package com.auto1.game;

import com.auto1.game.entity.SavedGame;
import com.auto1.game.entity.User;
import com.auto1.game.util.InputValidator;

public class GameFactory {
	
	public static void startGame(User user) {
		System.out.println("So which Char would you like to play today pick your options from below");
		int selectedGame = InputValidator.validateInputIntOption(3);
		Game game = null;
		switch(selectedGame) {
		case 1:
			game = new Sherlock();
			break;
		default:
			System.out.println("Incorrect input try again");
			startGame(user);
		}
		if (null != game) {
			game.play(1,5,user);
		}
	}

	public static void resumeGame(User user, SavedGame savedGame) {
		
		Game game = null;
		switch(savedGame.getGameType()) {
		case 1:
			game = new Sherlock();
			break;
		default:
			System.out.println("Incorrect input try again");
			startGame(user);
		}
		if (null != game) {
			game.play(savedGame.getLevel(),savedGame.getGameType(),user);
		}
	}

}
