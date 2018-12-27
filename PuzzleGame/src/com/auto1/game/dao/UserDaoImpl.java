package com.auto1.game.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import com.auto1.game.entity.User;

public class UserDaoImpl implements UserDao {
	
	private static final String fileName = "users.csv";
	private static final String seperator = ";";

	@Override
	public void increaseUserExperience(String userId, int experienceLevel) {
		StringBuilder sb = new StringBuilder();
		try (Stream<String> stream = Files.lines(Paths.get(fileName), Charset.defaultCharset());) {
			stream.forEach(s -> {

				if(s.startsWith(userId+seperator)) {
					String[] lineTokens = s.split(seperator);
					if (userId.equals(lineTokens[0]) && experienceLevel > Integer.parseInt(lineTokens[1])) {
						s = userId + seperator+experienceLevel;
					}
				}
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

	@Override
	public User findUser(String userId) {
		try (Stream<String> stream = Files.lines(Paths.get(fileName), Charset.defaultCharset());) {
			String line = stream.filter(s -> s.contains(userId+seperator))
					            .findFirst()
					            .orElse(null);
			if (null != line) {
				String[] lineTokens = line.split(seperator);
				if (lineTokens.length >= 2) {				
					return new User(lineTokens[0], Integer.parseInt(lineTokens[1]));
					
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
	public void insertUser(User newUser) {
		try (FileWriter fw = new FileWriter(fileName,true);) {
			fw.write(System.getProperty("line.separator"));
			fw.write(newUser.getUserId()+seperator+newUser.getExperience());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
