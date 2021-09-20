package ca.sheridancollege.newbytex.security;

import ca.sheridancollege.newbytex.beans.User;
import ca.sheridancollege.newbytex.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final UserService userService;
    private final JwtProvider jwtProvider;

    @Value("${hostname_spring}")
    private String hostname;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,HttpServletResponse response, Authentication authentication) throws IOException {
        DefaultOAuth2User oAuth2User = (DefaultOAuth2User) authentication.getPrincipal();
        String email = (String) oAuth2User.getAttributes().get("email");
        String username = (String) oAuth2User.getAttributes().get("name");
        String address = (String) oAuth2User.getAttributes().get("address");
        String phonenumber = (String) oAuth2User.getAttributes().get("phonenumber");
        String token = jwtProvider.createToken(email, "USER");
        User user = userService.findUserByEmail(email);

        if (user == null) {
            userService.registerOauthUser(email, username, address, phonenumber);
        } else {
            userService.updateOauthUser(user, username);
        }
        String uri = UriComponentsBuilder.fromUriString("http://" + hostname + "/oauth2/redirect")
                .queryParam("token", token)
                .build().toUriString();
        getRedirectStrategy().sendRedirect(request, response, uri);
    }

}
