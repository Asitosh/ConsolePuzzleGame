package com.auto1.game.dao;

import com.auto1.game.entity.User;

public interface UserDao {

	void increaseUserExperience(String string, int experienceLevel);

	User findUser(String userId);

	void insertUser(User newUser);

}
