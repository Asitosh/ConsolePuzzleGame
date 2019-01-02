package com.asitosh.game.util;

import java.util.Scanner;

import com.asitosh.game.dao.UserDao;
import com.asitosh.game.dao.UserDaoImpl;
import com.asitosh.game.entity.User;

public class UserProfile {
	
	private UserDao userDao = new UserDaoImpl();
	
	
	public UserProfile() {
		super();
		this.userDao = new UserDaoImpl();
	}

	public UserProfile(UserDao userDao) {
		super();
		this.userDao = userDao;
	}

	/**
	 * The method checkUser asks user for option to retrieve existing user or create new user 
	 * and depending on the option returns the instance of new or existing user.
	 * 
	 * @return User
	 */
	public User checkUser() {
	    System.out.println("Press 1 for new user 2 for existing user");
		Scanner in = new Scanner(System.in);
		int validatedInput = InputValidator.validateInputIntOption(in, 2);
		User user = null;
		if(1 == validatedInput) {
			user = createNewUser(in);
		} else {
			user = validateUser(in);
		}
		
		return user;
		
	}

	/**
	 * The method validateUser asks for userName from the user and validates 
	 * if the user present in persistence layer returns the user
	 * else if user is not present in persistence layer it returns null
	 * @param in 
	 * 
	 * @return User
	 */
	private User validateUser(Scanner in) {
		System.out.println("Please enter your userId");
		String userId = in.nextLine();
		User user =  userDao.findUser(userId);
		if (null != user) {
			System.out.println("Found the user");
			System.out.println("Welcome Back "+ user.getUserId());
			System.out.println("Your experience level is "+user.getExperience());
			return user;
		} else {
			System.out.println("User not found please try again");
			return checkUser();
		}
		
	}

	/**
	 * The method createNewUser asks for userName from the user and validates 
	 * if the user is already present asks for different userName
	 * else creates a new user in persistence layer and returns it.
	 * @param in 
	 * 
	 * @return User
	 */
	private User createNewUser(Scanner in) {
		System.out.println("Please enter a username of length 4 you would like to have");
		String userId = in.nextLine();
		while (!(4==userId.length())) {
			System.out.println("sorry we only accept 4 digit username");
			userId = in.nextLine();
		}
		User user =  userDao.findUser(userId);
		if (null != user) {
			System.out.println("Sorry the username already exist please try different user name");
			createNewUser(in);
		} else {
			System.out.println("Creating new user");
			User newUser = new User(userId,0);
			userDao.insertUser(newUser);
			System.out.println("Welcome "+ newUser.getUserId());
			System.out.println("Your experience level is "+newUser.getExperience());
			return newUser;
		}
		return null;
	}

}
