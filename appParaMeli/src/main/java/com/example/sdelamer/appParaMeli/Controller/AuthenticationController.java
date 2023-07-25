package com.example.sdelamer.appParaMeli.Controller;

import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sdelamer.appParaMeli.Model.LoginRequest;
import com.example.sdelamer.appParaMeli.Model.Security.AuthenticationResponse;
import com.example.sdelamer.appParaMeli.Service.SecurityService.AuthenticationService;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
	@Autowired
	AuthenticationService authenticationService;
	
	@GetMapping("/login")
	public ResponseEntity<String> testGet() throws LoginException {
		return ResponseEntity.ok("yess");
	}

	@PostMapping("/login")
	public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequest loginRequest) throws LoginException {
		return ResponseEntity.ok(authenticationService.loginUser(loginRequest));
	}

}
