package com.example.sdelamer.appParaMeli.Service.SecurityService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.sdelamer.appParaMeli.Model.AppUser;
import com.example.sdelamer.appParaMeli.Repository.AppUserRepository;

@Service
@Transactional
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	AppUserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AppUser appUser = userRepository.findByEmail(username);
		if (appUser != null) {
			return createAuthorizedUserFromAppUser(appUser);
		}
		return null;
	}

	private UserDetails createAuthorizedUserFromAppUser(@NonNull AppUser appUser) {
		List<GrantedAuthority> list2 = new ArrayList<>();
		list2.add(new SimpleGrantedAuthority("ROLE_USER"));
		return new User(appUser.getEmail(), appUser.getPassword(), list2);

	}
}
