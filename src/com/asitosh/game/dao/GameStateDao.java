package com.asitosh.game.dao;

import com.asitosh.game.entity.SavedGame;

public interface GameStateDao {

	void saveGame(String userId, int level, int life);

	SavedGame findSavedGame(String userId);

	void deleteSavedGame(String userId);

}
