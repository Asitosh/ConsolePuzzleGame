package com.auto1.game.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import com.auto1.game.entity.SavedGame;

public class GameStateDaoImpl implements GameStateDao {
	
	private static final String fileName = "savedGame.csv";

	private static final String seperator = ";";

	@Override
	public void saveGame(String userId, int level, int life) {
		try (FileWriter fw = new FileWriter(fileName,true)) {
			fw.write(System.getProperty("line.separator"));
		    fw.write(userId+seperator+level+seperator+life);
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
		
	}

	@Override
	public SavedGame findSavedGame(String userId) {
		try (Stream<String> stream = Files.lines(Paths.get(fileName), Charset.defaultCharset());) {
			String line = stream.filter(s -> s.startsWith(userId+seperator))
					            .findFirst()
					            .orElse(null);
			if (null != line) {
				String[] lineTokens = line.split(seperator);
				if (lineTokens.length >= 3) {
					
					return new SavedGame(1, Integer.parseInt(lineTokens[1])
							, Integer.parseInt(lineTokens[2]), lineTokens[0]);
					
				}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return null;
	}

	@Override
	public void deleteSavedGame(String userId) {
		StringBuilder sb = new StringBuilder();
		try (Stream<String> stream = Files.lines(Paths.get(fileName), Charset.defaultCharset());) {
			stream.filter(s -> !s.startsWith(userId+seperator))
			.forEach(s -> {
				sb.append(s);
				sb.append(System.getProperty("line.separator"));
			});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try(FileWriter fw = new FileWriter(fileName,false);) {
			fw.write(sb.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
