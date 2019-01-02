package com.asitosh.game.dao;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import com.asitosh.game.KeyNotFoundExcetion;
import com.asitosh.game.entity.Puzzle;

public class PuzzleDaoImpl implements PuzzleDao {
	
	private static final String fileName = "Sherlock.csv";

	private static final String seperator = ";";
	
	private static final String prefix = "SHERLOCK";

	@Override
	public Puzzle getPuzzle(String key) throws KeyNotFoundExcetion {
		Puzzle puzzle = new Puzzle();
		try (Stream<String> stream = Files.lines(Paths.get(fileName), Charset.defaultCharset());) {
			String line = stream.filter(s -> s.startsWith(prefix+key))
					            .findFirst()
					            .orElse(null);
			if (null != line) {
				String[] lineTokens = line.split(seperator);
				if (lineTokens.length >= 3) {
					puzzle.setQuestion(lineTokens[1]);
					puzzle.setAnswer(lineTokens[2]);
					return puzzle;
					
				}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return null;
	}

}
