package ca.sheridancollege.newbytex.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.sheridancollege.newbytex.beans.User;

public interface UserRepository extends JpaRepository<User, Long> {

	public User findByName(String name);
	
	public User findByEmail(String email);

	public User findByAddress(String address);

	public User findByPhonenumber(String phonenumber);

	public User findByActivationCode(String code);

	public User findByPasswordResetCode(String code);

}
