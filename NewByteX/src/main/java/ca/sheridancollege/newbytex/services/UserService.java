package ca.sheridancollege.newbytex.services;

import java.text.MessageFormat;
import java.util.Optional;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ca.sheridancollege.newbytex.beans.ConfirmationToken;
import ca.sheridancollege.newbytex.beans.User;
import ca.sheridancollege.newbytex.exceptions.UserNotFoundException;
import ca.sheridancollege.newbytex.repositories.UserRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final ConfirmationTokenService confirmationTokenService;
	private final EmailSenderService emailSenderService;

	@Override
	public UserDetails loadUserByUsername(String email) throws UserNotFoundException {

		final Optional<User> user = userRepository.findUserByEmail(email);

		if (user.isPresent()) {
			return user.get();
		} else {
			throw new UserNotFoundException(MessageFormat.format("User with email {0} cannot be found.", email));
		}
	}

	public void signUpUser(User user) {

		final String encryptedPassword = passwordEncoder.encode(user.getPassword());

		user.setPassword(encryptedPassword);

		final User createdUser = userRepository.save(user);

		final ConfirmationToken confirmationToken = new ConfirmationToken(user);

		confirmationTokenService.saveConfirmationToken(confirmationToken);

	}

	public void confirmUser(ConfirmationToken confirmationToken) {

		final User user = confirmationToken.getUser();

		user.setEnabled(true);

		userRepository.save(user);

		confirmationTokenService.deleteConfirmationToken(confirmationToken.getId());

	}


}
