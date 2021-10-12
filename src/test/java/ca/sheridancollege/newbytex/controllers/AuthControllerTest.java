package ca.sheridancollege.newbytex.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.naming.AuthenticationException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import ca.sheridancollege.newbytex.beans.User;
import ca.sheridancollege.newbytex.dto.PasswordResetRequestDTO;
import ca.sheridancollege.newbytex.exceptions.ApiRequestException;
import ca.sheridancollege.newbytex.repositories.UserRepository;

public class AuthControllerTest extends AbstractTest {
	
	@Autowired
	UserRepository userRepository;

	@Test
	public void forgotTest() throws Exception {
		String uri = "/api/auth/forgot";
		PasswordResetRequestDTO passwordReset = new PasswordResetRequestDTO();
		passwordReset.setEmail("testindmix@gmail.com");

		String input = super.mapToJson(passwordReset);

		MvcResult mvcResult = mvc
				.perform(MockMvcRequestBuilders
				.post(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(input))
				.andReturn();
		
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "Password reset code has been sent to your email");
	}
	
	@Test(expected = ApiRequestException.class)
	public void forgotTestException() throws Exception {
		String uri = "/api/auth/forgot";
		PasswordResetRequestDTO passwordReset = new PasswordResetRequestDTO();
		passwordReset.setEmail("testindmi@gmail.com"); //invalid email

		String input = super.mapToJson(passwordReset);

		MvcResult mvcResult = mvc
				.perform(MockMvcRequestBuilders
				.post(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(input))
				.andReturn();
		
		String content = mvcResult.getResponse().getContentAsString();
	}
	
	@Test
	public void resetTest() throws Exception {
		String uri = "/api/auth/reset";
		PasswordResetRequestDTO passwordReset = new PasswordResetRequestDTO();
		passwordReset.setPasswordResetCode("12345");
		passwordReset.setPassword("newpassword");
		
		User user = userRepository.findByEmail("testindmix@gmail.com");
		user.setPasswordResetCode("12345");
		userRepository.save(user);

		String input = super.mapToJson(passwordReset);

		MvcResult mvcResult = mvc
				.perform(MockMvcRequestBuilders
				.post(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(input))
				.andReturn();
		
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "Password reset code has been sent to your email");
	}
	
	@Test(expected = AuthenticationException.class)
	public void resetTestException() throws Exception {
		String uri = "/api/auth/reset";
		PasswordResetRequestDTO passwordReset = new PasswordResetRequestDTO();
		passwordReset.setPasswordResetCode("12345");
		passwordReset.setPassword("newpassword");
		
		User user = userRepository.findByEmail("testindmix@gmail.com");
		user.setPasswordResetCode("1234");
		userRepository.save(user);

		String input = super.mapToJson(passwordReset);

		MvcResult mvcResult = mvc
				.perform(MockMvcRequestBuilders
				.post(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(input))
				.andReturn();
		
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "Password reset code has been sent to your email");
	}

}
