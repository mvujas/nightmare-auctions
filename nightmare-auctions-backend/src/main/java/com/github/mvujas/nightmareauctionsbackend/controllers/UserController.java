package com.github.mvujas.nightmareauctionsbackend.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.mvujas.nightmareauctionsbackend.controllers.messages.UserRegistrationMessage;
import com.github.mvujas.nightmareauctionsbackend.model.User;
import com.github.mvujas.nightmareauctionsbackend.services.UserService;

@RestController
@RequestMapping("${api.url}/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping
	public void registerUser(
			@Valid @RequestBody UserRegistrationMessage userRegistrationData) {
		userService.registerUser(userRegistrationData);
	}
	
	@GetMapping("/{username}")
	public User getUserByUsername(@PathVariable String username) {
		return userService.getUserByUsername(username);
	}
	

}
