package com.asitosh.game;

import java.util.Scanner;

import com.asitosh.game.dao.GameStateDao;
import com.asitosh.game.dao.GameStateDaoImpl;
import com.asitosh.game.dao.PuzzleDao;
import com.asitosh.game.dao.PuzzleDaoImpl;
import com.asitosh.game.dao.UserDao;
import com.asitosh.game.dao.UserDaoImpl;
import com.asitosh.game.entity.Puzzle;
import com.asitosh.game.entity.User;

public class Sherlock implements Game{
	
	

	private static final int maxLevel = 3;
	
	private PuzzleDao puzzleDao;
	
	private UserDao userDao;
	
	private GameStateDao gameStateDao;
	
	public Sherlock() {
		super();
		this.puzzleDao = new PuzzleDaoImpl();
		this.userDao = new UserDaoImpl();
		this.gameStateDao = new GameStateDaoImpl();
	}
	
	

	public Sherlock(PuzzleDao puzzleDao, UserDao userDao, GameStateDao gameStateDao) {
		super();
		this.puzzleDao = puzzleDao;
		this.userDao = userDao;
		this.gameStateDao = gameStateDao;
	}



	@Override
	public void play(int level, int life, User user) {
		Scanner in = new Scanner(System.in);
		System.out.println("Lets Start the game");
		System.out.println("You will be given some situations and you will have to find the culprit");
		System.out.println("You have "+life+" life with you. for every incorrect answer you loose 1");
		System.out.println("NOTE: you have to type the first name of the culprit in any small or upper case to get the answer to be correct ");
		try {
			for (int i = level; i <= maxLevel; i++) {
				System.out.println("You can pause the game at current level and quit by simply typing 'quit'");
				System.out.println("@@@@@\t LEVEL "+i +"\t@@@@@@@@");
				Puzzle ques = puzzleDao.getPuzzle(Integer.toString(i));
				System.out.println("Level "+i+": Question");
				String question = ques.getQuestion();
				System.out.println();
				System.out.println();
				for (String s: question.split("::::")) {
					System.out.println(s);
				}
				System.out.println();
				System.out.println();
				String userAnswer = "";
				boolean levelNotPassed = true;
				boolean pauseAndExit = false;
				do {
					if (life < 1) {
						break;
					}
					userAnswer = in.nextLine();
					
					if (userAnswer.equalsIgnoreCase("quit")) {
						pauseAndExit = true;
						break;
					}
					if (userAnswer.equalsIgnoreCase(ques.getAnswer())) {
						levelNotPassed = false;
					} else {
						System.out.println("You have entered incorrect answer. Please try again");
						life--;
						System.out.println("Careful you are loosing life. you have "+ life + " life left");			
						
						
					}
					
				} while (levelNotPassed);
				if (pauseAndExit) {
					System.out.println("You have pressed quit");
					System.out.println("Saving the game");
					saveGameState(user, level, life);
					System.out.println("Shutting down");
					System.out.println("You can restart the game from level " + level);
					break;
				}
				if (life < 1) {
					System.out.println(",,,,Sorry you have no more life left to play the game,,,,,");
					break;
				}
				System.out.println("****\t Hurray Correct answer now we move to next level \t****");
				increaseUserExperience(user, level);
				System.out.println("######\t Your experience level is now increased \t######");
				level++;
								
			}
			if (level>maxLevel) {
				System.out.println("You have completed all the levels");
			}
		} catch (Exception e) {
			System.out.println("Oops some error has occured. Please restart the game");
		}
		in.close();
		
	}


	private void saveGameState(User user, int level, int life) {
		gameStateDao.saveGame(user.getUserId(), level, life);
		
	}

	private void increaseUserExperience(User user, int experienceLevel) {
		
		userDao.increaseUserExperience(user.getUserId(), experienceLevel);
	}

	

}
