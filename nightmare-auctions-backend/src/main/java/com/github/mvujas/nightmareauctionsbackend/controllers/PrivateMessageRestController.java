package com.github.mvujas.nightmareauctionsbackend.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.github.mvujas.nightmareauctionsbackend.model.User;
import com.github.mvujas.nightmareauctionsbackend.presentationview.UserPresentationView;
import com.github.mvujas.nightmareauctionsbackend.services.PrivateMessageService;

@RestController
@RequestMapping("${api.url}/message")
public class PrivateMessageRestController {

	@Autowired
	private PrivateMessageService privateMessageService;

	@GetMapping("/{username}")
	@JsonView(UserPresentationView.UsernameOnly.class)
	private List<User> getAllChattersForUser(
			@PathVariable(required = true) String username,
			Principal principal) {
		
		return privateMessageService.getChattersOrderedByDateForUser(username);
	}

}
