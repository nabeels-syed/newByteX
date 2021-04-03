package ca.sheridancollege.newbytex.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import ca.sheridancollege.newbytex.security.JwtConfigurer;
import ca.sheridancollege.newbytex.security.OAuth2SuccessHandler;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	private final JwtConfigurer jwtConfigurer;
	private final OAuth2SuccessHandler oauthSuccessHandler;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable().authorizeRequests().and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
				.antMatchers("/api/auth/**", "/api/auth/login", "/api/registration/**", "/h2-console/**")
				.permitAll().anyRequest().authenticated();
				//.and().oauth2Login().authorizationEndpoint()
				//.baseUri("/api/login/google").and().successHandler(oauthSuccessHandler).and().apply(jwtConfigurer);
		http.headers().frameOptions().disable();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}