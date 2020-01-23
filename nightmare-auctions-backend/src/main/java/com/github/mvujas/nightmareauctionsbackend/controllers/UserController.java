package com.github.mvujas.nightmareauctionsbackend.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.mvujas.nightmareauctionsbackend.controllers.messages.UserRegistrationMessage;
import com.github.mvujas.nightmareauctionsbackend.services.UserService;

@RestController
@RequestMapping("${api.url}/user")
public class UserController {

	@Autowired
	private UserService userService;
	

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping
	public void registerUser(
			@Valid @RequestBody UserRegistrationMessage userRegistrationData) {
		System.out.println(userRegistrationData);
		
		userService.registerUser(
				userRegistrationData.getUsername(), 
				passwordEncoder.encode(userRegistrationData.getPassword()), 
				userRegistrationData.getEmail());
	}
	

}
