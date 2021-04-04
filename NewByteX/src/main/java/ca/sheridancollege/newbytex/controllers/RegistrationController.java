package ca.sheridancollege.newbytex.controllers;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import ca.sheridancollege.newbytex.controllers.utils.ControllerUtils;
import ca.sheridancollege.newbytex.dto.RegistrationRequestDTO;
import ca.sheridancollege.newbytex.exceptions.ApiRequestException;
import ca.sheridancollege.newbytex.exceptions.EmailException;
import ca.sheridancollege.newbytex.exceptions.InputFieldException;
import ca.sheridancollege.newbytex.exceptions.PasswordException;
import ca.sheridancollege.newbytex.mapper.UserMapper;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/registration")
public class RegistrationController {

	private final UserMapper userMapper;
	private final RestTemplate restTemplate;

	@PostMapping
	public ResponseEntity<String> registerUser(@Valid @RequestBody RegistrationRequestDTO user, BindingResult bindingResult) {
		
		if (ControllerUtils.isPasswordDifferent(user.getPassword(), user.getPassword2())) {
			throw new PasswordException("Passwords do not match");
		}
		if (bindingResult.hasErrors()) {
			throw new InputFieldException(bindingResult);
		}
		if (!userMapper.registerUser(user)) {
			throw new EmailException("Email is already registered");
		}
		
		return ResponseEntity.ok("User registered");
	}
	
	@GetMapping("/activate/{token}")
	public ResponseEntity<String> activateUser(@PathVariable String token) {
		if (!userMapper.activateUser(token)) {
			throw new ApiRequestException("Activation token not found", HttpStatus.NOT_FOUND);
		} else {
			return ResponseEntity.ok("User activated");
		}
	}
}