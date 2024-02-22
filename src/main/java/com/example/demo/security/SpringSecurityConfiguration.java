package com.example.demo.security;

import static org.springframework.security.config.Customizer.withDefaults;
import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {

	@Bean
	public InMemoryUserDetailsManager createUserDetailsManager() {

		UserDetails userdetails1 = createNewUser("in28minutes", "dummy");
		UserDetails userdetails2 = createNewUser("ranga", "dummy1");

		return new InMemoryUserDetailsManager(userdetails1, userdetails2);
	}

	public UserDetails createNewUser(String username, String password) {
		Function<String, String> passwordEncoder = input -> passwordEncoder().encode(input);

		// UserDetails userdetails = User.withDefaultPasswordEncoder()
		UserDetails userdetails = User.builder().passwordEncoder(passwordEncoder).username(username).password(password)
				.roles("admin", "user").build();
		return userdetails;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder();

	}

	// All URLs are protected
	// A login form is shown for unauthorized requests
	// CSRF disable
	// Frames
	@Bean
	public SecurityFilterChain filterchain(HttpSecurity http) throws Exception {
		
		
		http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated());
		
		http.formLogin(withDefaults());
		
		http.csrf(csrf-> csrf.disable());
		
		http.headers(headers-> headers.frameOptions(frame -> frame.disable()));
		
		return http.build();
		
		
	}
}
