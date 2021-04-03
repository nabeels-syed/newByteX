package ca.sheridancollege.newbytex.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.sheridancollege.newbytex.beans.ConfirmationToken;
import ca.sheridancollege.newbytex.beans.User;
import ca.sheridancollege.newbytex.exceptions.UserNotFoundException;
import ca.sheridancollege.newbytex.repositories.UserRepository;
import ca.sheridancollege.newbytex.services.ConfirmationTokenService;
import ca.sheridancollege.newbytex.services.EmailSenderService;

@RestController
@RequestMapping("/api")
public class LoginController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	EmailSenderService emailSenderService;

	@Autowired
	ConfirmationTokenService confirmationTokenService;

	@PostMapping("/validatelogin")
	public Boolean validateLogin(@RequestBody User userToValidate) {
		User user;
		try {
			user = userRepository.findUserByEmail(userToValidate.getEmail())
					.orElseThrow(() -> new UserNotFoundException("Email not found"));
		} catch (UserNotFoundException userNotFoundException) {
			return false;
		}

		return passwordEncoder.matches(userToValidate.getPassword(), user.getPassword()); 
	}

	@PostMapping("/registeruser")
	public Boolean registerUser(@RequestBody User user) {

		try {
			String password = user.getPassword().length()>=8 ? user.getPassword() : null;
			if(password == null) return false;
			user.setPassword(passwordEncoder.encode(password));
			user = userRepository.save(user);

			final ConfirmationToken confirmationToken = new ConfirmationToken(user);
			confirmationTokenService.saveConfirmationToken(confirmationToken);
			//emailSenderService.sendConfirmationEmail(user.getEmail(), emailSenderService.getSubject(),
					//confirmationToken);

		} catch (Exception e) {
			return false;
		}
		return true;
	}

}
