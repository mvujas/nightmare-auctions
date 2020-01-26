package com.github.mvujas.nightmareauctionsbackend.controllers;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.github.mvujas.nightmareauctionsbackend.controllers.messages.PrivateMessageMessageHolder;
import com.github.mvujas.nightmareauctionsbackend.exceptionhandling.exceptions.ResourceOperationException;
import com.github.mvujas.nightmareauctionsbackend.model.PrivateMessage;
import com.github.mvujas.nightmareauctionsbackend.model.User;
import com.github.mvujas.nightmareauctionsbackend.presentationview.MessagePresentationView;
import com.github.mvujas.nightmareauctionsbackend.presentationview.UserPresentationView;
import com.github.mvujas.nightmareauctionsbackend.services.PrivateMessageService;

@RestController
@RequestMapping("${api.url}/message")
public class PrivateMessageRestController {

	@Autowired
	private PrivateMessageService privateMessageService;

	@GetMapping("/{username}")
	@JsonView(UserPresentationView.UsernameOnly.class)
	@PreAuthorize("isAuthenticated()")
	public List<User> getAllChattersForUser(
			@PathVariable(required = true) String username,
			Principal principal) {
		if(!principal.getName().equals(username)) {
			throw new ResourceOperationException(
					"User can see only their own waiting grades");
		}
		return privateMessageService.getChattersOrderedByDateForUser(username);
	}

	@PostMapping("/{username}")
	@PreAuthorize("isAuthenticated()")
	public void sendMessage(
			@Valid @RequestBody(required = true) PrivateMessageMessageHolder messageHolder,
			@PathVariable(required = true) String username,
			Principal principal) {
		privateMessageService.saveMessage(
				principal.getName(), username, messageHolder.getText());
	}
	
	@GetMapping("/{requesterUsername}/{otherUsername}")
	@JsonView(MessagePresentationView.MessagesBetweenUsersView.class)
	@PreAuthorize("isAuthenticated()")
	public List<PrivateMessage> getPrivateMessagesBetweenUsers(
			@PathVariable(required = true) String requesterUsername,
			@PathVariable(required = true) String otherUsername,
			@RequestParam(required = false) 
			@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") 
				Date since,
			Principal principal) {
		if(since == null) {
			since = new Date(0);
		}
		if(!principal.getName().equals(requesterUsername)) {
			throw new ResourceOperationException(
					"Users can send messages only on their own name");
		}
		return privateMessageService
				.getMessagesBetweenUsersSince(requesterUsername, otherUsername, since);
	}
	
}
