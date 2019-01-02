package com.auto1.game.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.auto1.game.dao.UserDao;
import com.auto1.game.entity.User;

import junit.framework.Assert;


public class UserProfileTest {
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
	
	private UserProfile underTest;
	
	private UserDao userDaoMock;
	
	User testUser;
	
	@Before
	public void setUp() {
		System.setOut(new PrintStream(outContent));
		userDaoMock = EasyMock.createNiceMock(UserDao.class);
		this.underTest = new UserProfile(userDaoMock);
		testUser = new User("abcd", 1);
		
	}
	
	@Test
	public void testCheckExistingUser() {
		
		EasyMock.expect(this.userDaoMock.findUser(EasyMock.anyString())).andReturn(testUser);
		EasyMock.replay(this.userDaoMock);
		String newLine = System.getProperty("line.separator");
		System.setIn(new ByteArrayInputStream(("2"+newLine+"abcd").getBytes()));
		
		User responseUser = this.underTest.checkUser();
		Assert.assertEquals(testUser, responseUser);
		
	}
	
	@After
	public void restoreStreams() {
	    System.setOut(originalOut);
	    System.setIn(originalIn);
	}

}
