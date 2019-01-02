package com.auto1.game;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.auto1.game.entity.User;

public class SherLockTest {
	/**
	 * outContent to hold System.out string to test
	 */
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	/**
	 * set back to original PrintStream
	 */
	private final PrintStream originalOut = System.out;
	/**
	 * set back to original InputStream
	 */
	private final InputStream originalIn = System.in;
	
	
	
	/**
	 * original test class
	 */
	private Sherlock undertest = new Sherlock();
	
	@Before
	public void setUp() {
		System.setOut(new PrintStream(outContent));
	}
	@Test
	public void testPlayWithIncorrectAnswer() {
		User TestUser = new User("test",0);
		String newLine = System.getProperty("line.separator");
		System.setIn(new ByteArrayInputStream(("abcd"+newLine+"abcd"+newLine+"abcd"+newLine+"abcd"+newLine+"abcd").getBytes()));
		this.undertest.play(1, 5, TestUser);
		
		Assert.assertEquals(true, outContent.toString().contains(",,,,Sorry you have no more life left to play the game,,,,,"));
		
	}
	
	@Test
	public void testPlayWithCorrectAnswer() {
		User TestUser = new User("test",0);
		String newLine = System.getProperty("line.separator");
		System.setIn(new ByteArrayInputStream(("mark"+newLine+"jason"+newLine+"britney").getBytes()));
		this.undertest.play(1, 5, TestUser);
		
		Assert.assertEquals(true, outContent.toString().contains("You have completed all the levels"));
		
	}
	
	@After
	public void restoreStreams() {
	    System.setOut(originalOut);
	    System.setIn(originalIn);
	}

}
