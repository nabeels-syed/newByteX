//package ca.sheridancollege.newbytex.controllers;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import ca.sheridancollege.newbytex.dto.MusicTrackRequestDTO;
//import ca.sheridancollege.newbytex.dto.PasswordResetRequestDTO;
//import ca.sheridancollege.newbytex.dto.RegistrationRequestDTO;
//import ca.sheridancollege.newbytex.repositories.MusicTrackRepository;
//import ca.sheridancollege.newbytex.repositories.UserRepository;
//
//class MusicTrackControllerTest extends AbstractTest{
//
//	@Autowired
//	MusicTrackRepository musicTrackRepository;
//
//	@Test
//	void getAllTracksTest() throws Exception {
//		
//		String uri = "/api/track/getAllTracks";
//
//		MvcResult mvcResult = mvc
//				.perform(MockMvcRequestBuilders
//				.post(uri)
//				.contentType(MediaType.APPLICATION_JSON_VALUE)
//				.content(""))
//				.andReturn();
//		
//		assertEquals(200, mvcResult.getResponse().getStatus());
//	}
//}
