package com.auto1.game.util;

import java.util.Scanner;

import com.auto1.game.dao.UserDao;
import com.auto1.game.dao.UserDaoImpl;
import com.auto1.game.entity.User;

public class UserProfile {
	
	private UserDao userDao = new UserDaoImpl();
	
	public User checkUser() {
	    System.out.println("Press 1 for new user 2 for existing user");
		
		
		int validatedInput = InputValidator.validateInputIntOption(2);
		User user = null;
		if(1 == validatedInput) {
			user = createNewUser();
		} else {
			user = validateUser();
		}
		
		return user;
		
	}

	private User validateUser() {
		System.out.println("Please enter your userId");
		Scanner in = new Scanner(System.in);
		String userId = in.nextLine();
		User user =  userDao.findUser(userId);
		if (null != user) {
			System.out.println("Found the user");
			return user;
		} else {
			System.out.println("User not found please try again");
			return checkUser();
		}
		
	}

	private User createNewUser() {
		System.out.println("Please enter a username of length 4 you would like to have");
		Scanner in = new Scanner(System.in);
		String userId = in.nextLine();
		while (!(4==userId.length())) {
			System.out.println("sorry we only accept 4 digit username");
			userId = in.nextLine();
		}
		User user =  userDao.findUser(userId);
		if (null != user) {
			System.out.println("Sorry the username already exist please try different user name");
			createNewUser();
		} else {
			System.out.println("Creating new user");
			User newUser = new User(userId,0);
			userDao.insertUser(newUser);
			return newUser;
		}
		return null;
	}

}
