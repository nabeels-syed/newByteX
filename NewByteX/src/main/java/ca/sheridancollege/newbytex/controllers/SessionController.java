package ca.sheridancollege.newbytex.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ca.sheridancollege.newbytex.beans.User;

@RestController
public class SessionController {

	static public void startSession(User user, HttpServletRequest request) {

		List<String> userInfo = new ArrayList<>();
		request.getSession().setAttribute("USER_INFO", userInfo);

		userInfo.add(user.getName());
		userInfo.add(user.getEmail());
		userInfo.add(user.getPhonenumber());
		userInfo.add(user.getAddress());
		userInfo.add(user.getPassword());

		request.getSession().setAttribute("USER_INFO", userInfo);

	}

	static public void endSession(HttpServletRequest request) {
		request.getSession().invalidate();
	}

}
