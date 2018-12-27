package com.auto1.game.dao;

import com.auto1.game.entity.SavedGame;

public interface GameStateDao {

	void saveGame(String userId, int level, int life);

	SavedGame findSavedGame(String userId);

	void deleteSavedGame(String userId);

}
