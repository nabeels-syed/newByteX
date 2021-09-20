package ca.sheridancollege.newbytex.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import ca.sheridancollege.newbytex.dto.RegistrationRequestDTO;
import ca.sheridancollege.newbytex.exceptions.EmailException;

class RegistrationControllerTest extends AbstractTest {

	@Test
	public void registrationTest() throws Exception {
		String uri = "/api/registration";
		RegistrationRequestDTO newUser = new RegistrationRequestDTO();
		newUser.setEmail("luc.laffin@gmail.com");
		newUser.setPassword("password");
		newUser.setName("Luc");

		String input = super.mapToJson(newUser);

		MvcResult mvcResult = mvc
				.perform(MockMvcRequestBuilders
				.post(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(input))
				.andReturn();
		
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "User registered");
	}
	
	@Test(expected = EmailException.class)
	public void activationTest() throws Exception {
		String uri = "/api/registration";
		RegistrationRequestDTO newUser = new RegistrationRequestDTO();
		newUser.setEmail("luc.laffingmail.com");
		newUser.setPassword("password");
		newUser.setName("Luc");

		String input = super.mapToJson(newUser);

		MvcResult mvcResult = mvc
				.perform(MockMvcRequestBuilders
				.post(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(input))
				.andReturn();
		
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "User registered");
	}

}
