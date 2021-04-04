package ca.sheridancollege.newbytex.dto;

import lombok.Data;

@Data
public class PasswordResetRequestDTO {
	private String email;
	private String password;
	private String password2;

}
