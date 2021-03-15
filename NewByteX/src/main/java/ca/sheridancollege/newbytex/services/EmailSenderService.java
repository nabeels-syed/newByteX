package ca.sheridancollege.newbytex.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import ca.sheridancollege.newbytex.beans.ConfirmationToken;

@Service
public class EmailSenderService {
	
	@Autowired
	private JavaMailSender javaMailSender;
	private String subject = "Confirm Your NDMIX Account";

	public void sendConfirmationEmail(String to, String subject, ConfirmationToken confirmationToken) {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(to);
		msg.setSubject(subject);
		String text = "Please click the following link to confirm your account: ";
		String url = "localhost:8080/api/confirmaccount?=" + confirmationToken.toString();
		msg.setText(text+url);
		try {
			javaMailSender.send(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getSubject() {
		return subject;
	}

}

