package ca.sheridancollege.newbytex.controllers;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


import org.junit.Test;

import ca.sheridancollege.newbytex.beans.User;

public class LoginControllerTest {

	@Test
	void testValidateLoginRegular() {
		LoginController loginController = new LoginController();
		User user = new User();
		user.setEmail("test@test.com");
		user.setPassword("validPassword");
		Boolean result = loginController.validateUser(user);
		assertTrue("Invalid login!", result);
	}
	
	@Test
	void testValidateLoginNameEmailException() {
		LoginController loginController = new LoginController();
		User user = new User();
		user.setEmail("noone@sheridancollege.ca"); //email does not belong to any user
		user.setPassword("validPassword");
		Boolean result = loginController.validateUser(user);
		assertFalse("Invalid login!", result);
	}
	
	@Test
	void testValidateLoginNameEmailException2() {
		LoginController loginController = new LoginController();
		User user = new User();
		user.setEmail("rgdffdglefca"); //not a valid email
		user.setPassword("validPassword");
		Boolean result = loginController.validateUser(user);
		assertFalse("Invalid login!", result);
	}
	
	@Test
	void testValidateLoginNamePasswordException() {
		LoginController loginController = new LoginController();
		User user = new User();
		user.setEmail("test@test.com"); 
		user.setPassword("passwordd"); //wrong password
		Boolean result = loginController.validateUser(user);
		assertFalse("Invalid login!", result);
	}
	
	@Test
	void testRegisterUserRegular() {
		LoginController loginController = new LoginController();
		User user = new User();
		user.setName("Test");
		user.setEmail("test2@test.com"); 
		user.setPassword("password"); 
		user.setAddress("123 Fake Street");
		user.setPhonenumber("111-111-1111");
		Boolean result = loginController.registerUser(user);
		assertTrue("Could not register user!", result);
	}
	
	@Test
	void testRegisterUserEmailNotUniqueException() {
		LoginController loginController = new LoginController();
		User user = new User();
		user.setName("Test");
		user.setEmail("test@test.com"); //not a unique email
		user.setPassword("password"); 
		user.setAddress("123 Fake Street");
		user.setPhonenumber("111-111-1111");
		Boolean result = loginController.registerUser(user);
		assertFalse("Could not register user!", result);
	}
	
	@Test
	void testRegisterUserNameBlankException() {
		LoginController loginController = new LoginController();
		User user = new User();
		user.setName(""); //blank name
		user.setEmail("test2@test.com");
		user.setPassword("password"); 
		user.setAddress("123 Fake Street");
		user.setPhonenumber("111-111-1111");
		Boolean result = loginController.registerUser(user);
		assertFalse("Could not register user!", result);
	}
	
	@Test
	void testRegisterUserPasswordBlankException() {
		LoginController loginController = new LoginController();
		User user = new User();
		user.setName("Name"); 
		user.setEmail("test2@test.com");
		user.setPassword(""); 
		user.setAddress("123 Fake Street");
		user.setPhonenumber("111-111-1111");
		Boolean result = loginController.registerUser(user);
		assertFalse("Could not register user!", result);
	}
	
	@Test
	void testRegisterUserPasswordBoundaryIn() {
		LoginController loginController = new LoginController();
		User user = new User();
		user.setName("Name"); 
		user.setEmail("test2@test.com");
		user.setPassword("12345678"); //min 8 chars
		user.setAddress("123 Fake Street");
		user.setPhonenumber("111-111-1111");
		Boolean result = loginController.registerUser(user);
		assertTrue("Could not register user!", result);
	}
	
	@Test
	void testRegisterUserPasswordBoundaryOut() {
		LoginController loginController = new LoginController();
		User user = new User();
		user.setName("Name"); 
		user.setEmail("test2@test.com");
		user.setPassword("1234567"); //1 char too short
		user.setAddress("123 Fake Street");
		user.setPhonenumber("111-111-1111");
		Boolean result = loginController.registerUser(user);
		assertFalse("Could not register user!", result);
	}
	
	@Test
	void testRegisterUserEmailBoundaryIn() {
		LoginController loginController = new LoginController();
		User user = new User();
		user.setName("Name"); 
		user.setEmail("t@a.c"); 
		user.setPassword("12345678"); 
		user.setAddress("123 Fake Street");
		user.setPhonenumber("111-111-1111");
		Boolean result = loginController.registerUser(user);
		assertTrue("Could not register user!", result);
	}
	@Test
	void testRegisterUserEmailBoundaryOut() {
		LoginController loginController = new LoginController();
		User user = new User();
		user.setName("Name"); 
		user.setEmail("@."); 
		user.setPassword("12345678"); 
		user.setAddress("123 Fake Street");
		user.setPhonenumber("111-111-1111");
		Boolean result = loginController.registerUser(user);
		assertFalse("Could not register user!", result);
	}

}
