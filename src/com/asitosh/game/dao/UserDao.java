package com.asitosh.game.dao;

import com.asitosh.game.entity.User;

public interface UserDao {

	void increaseUserExperience(String string, int experienceLevel);

	User findUser(String userId);

	void insertUser(User newUser);

}
