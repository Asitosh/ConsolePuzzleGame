package com.auto1.game.dao;

import com.auto1.game.KeyNotFoundExcetion;
import com.auto1.game.entity.Puzzle;

public interface PuzzleDao {
	
	

	public Puzzle getPuzzle(String key) throws KeyNotFoundExcetion;

}
