package com.auto1.game.entity;

public class SavedGame {
	
	private int gameType;
	private int level;
	private int life;
	private String UserId;
	
	public SavedGame(int gameType, int level, int life, String userId) {
		super();
		this.gameType = gameType;
		this.level = level;
		this.life = life;
		UserId = userId;
	}
	
	public int getGameType() {
		return gameType;
	}
	public void setGameType(int gameType) {
		this.gameType = gameType;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getLife() {
		return life;
	}
	public void setLife(int life) {
		this.life = life;
	}
	public String getUserId() {
		return UserId;
	}
	public void setUserId(String userId) {
		UserId = userId;
	}
	

}
