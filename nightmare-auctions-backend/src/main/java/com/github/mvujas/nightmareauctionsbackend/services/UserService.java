package com.github.mvujas.nightmareauctionsbackend.services;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.github.mvujas.nightmareauctionsbackend.controllers.messages.UserRegistrationMessage;
import com.github.mvujas.nightmareauctionsbackend.model.User;
import com.github.mvujas.nightmareauctionsbackend.repositories.RoleRepository;
import com.github.mvujas.nightmareauctionsbackend.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) 
			throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		
		if(user == null) {
			throw new UsernameNotFoundException(
					"User under given username doesn't exists");
		}
		
		return user;
	}

	public void registerUser(
			@Valid UserRegistrationMessage userRegistrationData) {
		
		User user = new User(
				userRegistrationData.getUsername(), 
				userRegistrationData.getEmail(), 
				passwordEncoder.encode(userRegistrationData.getPassword()));
		
		user.addRole(roleRepository.findByName("USER"));
		
		userRepository.saveAndFlush(user);
	}
	
	public User getUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
}