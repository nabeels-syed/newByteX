package ca.sheridancollege.newbytex.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import ca.sheridancollege.newbytex.beans.ConfirmationToken;
import ca.sheridancollege.newbytex.beans.User;
import ca.sheridancollege.newbytex.exceptions.UserNotFoundException;
import ca.sheridancollege.newbytex.repositories.ConfirmationTokenRepository;
import ca.sheridancollege.newbytex.repositories.UserRepository;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	ConfirmationTokenRepository confirmationTokenRepository;

	@PostMapping("/getallusers")
	public List<User> getAllUsers(Model model) {
		List<User> userList = userRepository.findAll();
		return userList;
	}

	@GetMapping("/getuserbyname/{name}")
	public User getUserByName(Model model, @PathVariable String name) {
		User user;
		try {
			user = userRepository.findUserByName(name).orElseThrow(() -> new UserNotFoundException("Email not found"));
		} catch (Exception e) {
			user = null;
		}
		return user;
	}

	@GetMapping("/getuserbyemail/{email}")
	public User getUserByEmail(Model model, @PathVariable String email) {
		User user;
		try {
			user = userRepository.findUserByEmail(email)
					.orElseThrow(() -> new UserNotFoundException("Email not found"));
			;
		} catch (Exception e) {
			user = null;
		}
		return user;
	}

	@GetMapping("/getuserbyaddress/{address}")
	public User getUserByAddress(Model model, @PathVariable String address) {
		User user;
		try {
			user = userRepository.findUserByAddress(address)
					.orElseThrow(() -> new UserNotFoundException("Email not found"));
			;
		} catch (Exception e) {
			user = null;
		}
		return user;
	}

	@GetMapping("/getuserbyphonenumber/{phonenumber}")
	public User getUserByPhoneNumber(Model model, @PathVariable String phonenumber) {
		User user;
		try {
			user = userRepository.findUserByPhonenumber(phonenumber)
					.orElseThrow(() -> new UserNotFoundException("Email not found"));
			;
		} catch (Exception e) {
			user = null;
		}
		return user;
	}

	@GetMapping("/confirmaccount")
	public Boolean confirmAccount(Model model, WebRequest request, @RequestParam String token) {

		List<ConfirmationToken> confirmationTokens = confirmationTokenRepository.findAll();
		ConfirmationToken confirmationToken = confirmationTokens.stream()
				  .filter(t -> token.equals(t.getConfirmationToken()))
				  .findAny()
				  .orElse(null);
		if (confirmationToken != null) {
			if (token.equals(confirmationToken.getConfirmationToken())) {
				User user = confirmationToken.getUser();
				//user.setEnabled(true);
				userRepository.save(user);
				return true;
			}
		}
		return false;

	}

}
