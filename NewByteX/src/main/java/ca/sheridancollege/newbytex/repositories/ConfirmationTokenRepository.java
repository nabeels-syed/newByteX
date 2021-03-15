package ca.sheridancollege.newbytex.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.sheridancollege.newbytex.beans.ConfirmationToken;
import ca.sheridancollege.newbytex.beans.User;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {
	
	public ConfirmationToken findAllByConfirmationToken(String confirmationToken);

}
