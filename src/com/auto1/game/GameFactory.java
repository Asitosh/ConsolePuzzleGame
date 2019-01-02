package com.auto1.game;

import java.util.Scanner;

import com.auto1.game.entity.SavedGame;
import com.auto1.game.entity.User;
import com.auto1.game.util.InputValidator;

public class GameFactory {
	
	public static void startGame(User user) {
		System.out.println("So which Char would you like to play today pick your options from below");
		System.out.println("Type 1 for Sherlock Holmes");
		System.out.println("Type 2 for SSpiderMan");
		Scanner in = new Scanner(System.in);
		int selectedGame = InputValidator.validateInputIntOption(in,3);
		Game game = null;
		switch(selectedGame) {
		case 1:
			game = new Sherlock();
			break;
		case 2:
			System.out.println("Sorry the Game you are searching is still in progress try another");
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
			System.out.println("Sorry the game was not found start new game");
			startGame(user);
		}
		if (null != game) {
			game.play(savedGame.getLevel(),savedGame.getGameType(),user);
		}
	}

}
