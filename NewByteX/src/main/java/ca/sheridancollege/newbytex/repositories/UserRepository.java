package ca.sheridancollege.newbytex.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.sheridancollege.newbytex.beans.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	public Optional<User> findUserByName(String name);
	public Optional<User> findUserByEmail(String email);
	public Optional<User> findUserByAddress(String address);
	public Optional<User> findUserByPhonenumber(String phonenumber);

}
