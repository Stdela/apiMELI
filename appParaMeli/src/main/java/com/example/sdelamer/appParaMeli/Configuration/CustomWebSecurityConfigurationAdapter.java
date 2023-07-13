package com.example.sdelamer.appParaMeli.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.sdelamer.appParaMeli.Service.SecurityService.MyUserDetailsService;

@Configuration
@EnableWebSecurity
public class CustomWebSecurityConfigurationAdapter {

	@Autowired
	MyUserDetailsService userDetailsService;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("user1").password((passwordEncoder().encode("123123")))
				.authorities("ROLE_USER");
		auth.userDetailsService(userDetailsService);
	}


	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(
				(requests) -> requests.requestMatchers("/")
				.permitAll()
				.anyRequest().authenticated());
		http.formLogin((formlogin) -> formlogin
//				.loginPage("/login")
				.permitAll());
		return http.build();
//		.and().httpBasic()
//		hasRole("ROLE_USER"); 

	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
