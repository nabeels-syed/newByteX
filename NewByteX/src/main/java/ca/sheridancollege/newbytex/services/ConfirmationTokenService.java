package ca.sheridancollege.newbytex.services;

import org.springframework.stereotype.Service;

import ca.sheridancollege.newbytex.beans.ConfirmationToken;
import ca.sheridancollege.newbytex.repositories.ConfirmationTokenRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {

	private final ConfirmationTokenRepository confirmationTokenRepository;

	public void saveConfirmationToken(ConfirmationToken confirmationToken) {

		confirmationTokenRepository.save(confirmationToken);
	}
	
	public void deleteConfirmationToken(Long id){

		confirmationTokenRepository.deleteById(id);
	}
	
}