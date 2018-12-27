package com.auto1.game;

import java.util.Scanner;

import com.auto1.game.dao.GameStateDao;
import com.auto1.game.dao.GameStateDaoImpl;
import com.auto1.game.dao.PuzzleDao;
import com.auto1.game.dao.PuzzleDaoImpl;
import com.auto1.game.dao.UserDao;
import com.auto1.game.dao.UserDaoImpl;
import com.auto1.game.entity.Puzzle;
import com.auto1.game.entity.User;

public class Sherlock implements Game{
	
	

	private static final int maxLevel = 3;
	
	private PuzzleDao puzzleDao = new PuzzleDaoImpl();
	
	private UserDao userDao = new UserDaoImpl();
	
	private GameStateDao gameStateDao = new GameStateDaoImpl();
	

	public static void startPLay() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void play(int level, int life, User user) {
		Scanner in = new Scanner(System.in);
		System.out.println("Lets Start the game");
		System.out.println("You will be given some situations and you will have to find the culprit");
		System.out.println("NOTE: you have to type the first name of the culprit in any small or upper case to get the answer to be correct ");
		try {
			for (int i = level; i <= maxLevel; i++) {
				System.out.println("@@@@@\t LEVEL "+i +"\t@@@@@@@@");
				Puzzle ques = puzzleDao.getPuzzle(Integer.toString(i));
				System.out.println("Level "+i+": Question");
				String question = ques.getQuestion();
				for (String s: question.split("::::")) {
					System.out.println(s);
				}			
				String userAnswer = "";
				boolean levelNotPassed = true;
				boolean pauseAndExit = false;
				do {
					userAnswer = in.nextLine();
					if (life < 1) {
						break;
					}
					if (userAnswer.equalsIgnoreCase("quit")) {
						pauseAndExit = true;
						break;
					}
					if (userAnswer.equalsIgnoreCase(ques.getAnswer())) {
						levelNotPassed = false;
					} else {
						System.out.println("You have entered incorrect answer. Please try again");
						System.out.println("Careful you are loosing life. you have "+ life + " life left");					
						
						life--;
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
			System.out.println("You have completed all the levels");
		} catch (KeyNotFoundExcetion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
