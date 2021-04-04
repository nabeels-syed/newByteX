package ca.sheridancollege.newbytex.controllers;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.sheridancollege.newbytex.controllers.utils.ControllerUtils;
import ca.sheridancollege.newbytex.dto.AuthenticationRequestDTO;
import ca.sheridancollege.newbytex.dto.PasswordResetRequestDTO;
import ca.sheridancollege.newbytex.dto.UserResponseDTO;
import ca.sheridancollege.newbytex.exceptions.ApiRequestException;
import ca.sheridancollege.newbytex.exceptions.PasswordConfirmationException;
import ca.sheridancollege.newbytex.exceptions.PasswordException;
import ca.sheridancollege.newbytex.mapper.UserMapper;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
	
	private final AuthenticationManager authenticationManager;
	private final UserMapper userMapper;
	
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(@RequestBody AuthenticationRequestDTO request) {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
			return ResponseEntity.ok(userMapper.login(request.getEmail()));
		} catch(AuthenticationException e) {
			throw new ApiRequestException("Incorrect email or password", HttpStatus.FORBIDDEN);
		}
	}
	
	@PostMapping("/forgot")
	public ResponseEntity<String> forgotPassword(@RequestBody PasswordResetRequestDTO passwordReset) {
		boolean sendEmail = userMapper.sendPasswordResetCode(passwordReset.getEmail());
		if(!sendEmail) {
			throw new ApiRequestException("Email not found", HttpStatus.BAD_REQUEST);
		}
		
		return ResponseEntity.ok("Password reset code has been sent to your email");
	}
	
	@GetMapping("/reset/{token}")
	public ResponseEntity<UserResponseDTO> getPasswordResetToken(@PathVariable String token) {
		UserResponseDTO user = userMapper.findByPasswordResetCode(token);
		if(user==null) {
			throw new ApiRequestException("Password reset token is invalid", HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok(user);
	}
	
	@PostMapping("/reset")
	public ResponseEntity<String> passwordReset(@RequestBody PasswordResetRequestDTO passwordReset) {
		if (ControllerUtils.isPasswordConfirmEmpty(passwordReset.getPassword2())) {
            throw new PasswordConfirmationException("Password confirmation cannot be empty");
        }
        if (ControllerUtils.isPasswordDifferent(passwordReset.getPassword(), passwordReset.getPassword2())) {
            throw new PasswordException("Passwords do not match");
        }
        userMapper.passwordReset(passwordReset.getEmail(), passwordReset.getPassword());
        return ResponseEntity.ok("Password changed");
	}

}
