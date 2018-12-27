package com.auto1.game.entity;

public class User {
	
	private String userId;
	
	private int experience;

	public User(String userId, int experience) {
		super();
		this.userId = userId;
		this.experience = experience;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

}
