package com.example.sdelamer.appParaMeli.Configuration.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.example.sdelamer.appParaMeli.Configuration.Security.jwt.JwtAuthenticationFilter;
import com.example.sdelamer.appParaMeli.Service.SecurityService.MyUserDetailsService;

@Configuration
@EnableWebSecurity(debug = true)

public class CustomWebSecurityConfigurationAdapter {

	@Autowired
	MyUserDetailsService userDetailsService;

	@Autowired
	JwtAuthenticationFilter jwtAuthenticationFilter;

	private final AuthenticationConfiguration configuration;

	public CustomWebSecurityConfigurationAdapter(AuthenticationConfiguration configuration) {
		this.configuration = configuration;
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//		auth.build();
	}

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.csrf().disable();
		http.authorizeHttpRequests((requests) -> {

			
				try {
					requests.requestMatchers("/auth/**","/error").permitAll().anyRequest().authenticated().and().sessionManagement()
							.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
				} catch (Exception e) {
					// TODO MANEJAR
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			

		})
		.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();

	}

	@Bean
	AuthenticationManager authenticationManager() throws Exception {
		return configuration.getAuthenticationManager();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
