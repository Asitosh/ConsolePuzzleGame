package com.asitosh.game.dao;

import com.asitosh.game.KeyNotFoundExcetion;
import com.asitosh.game.entity.Puzzle;

public interface PuzzleDao {
	
	

	public Puzzle getPuzzle(String key) throws KeyNotFoundExcetion;

}
