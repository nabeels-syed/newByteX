package ca.sheridancollege.newbytex.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;  

@Configuration  
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {  

	 @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http.authorizeRequests()
	                .antMatchers("/").permitAll()
	                .antMatchers("/h2-console/**").permitAll();

	        http.csrf().disable();
	        http.headers().frameOptions().disable();
	    }
	 
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}