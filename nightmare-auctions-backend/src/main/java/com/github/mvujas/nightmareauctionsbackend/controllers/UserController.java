package com.github.mvujas.nightmareauctionsbackend.controllers;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.github.mvujas.nightmareauctionsbackend.controllers.messages.UserRegistrationMessage;
import com.github.mvujas.nightmareauctionsbackend.exceptionhandling.exceptions.ResourceOperationException;
import com.github.mvujas.nightmareauctionsbackend.model.GradeHolder;
import com.github.mvujas.nightmareauctionsbackend.model.User;
import com.github.mvujas.nightmareauctionsbackend.presentationview.GradePresentationView;
import com.github.mvujas.nightmareauctionsbackend.presentationview.UserPresentationView;
import com.github.mvujas.nightmareauctionsbackend.services.GradeService;
import com.github.mvujas.nightmareauctionsbackend.services.UserService;

@RestController
@RequestMapping("${api.url}/user")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private GradeService gradeService;
	
	@PostMapping
	public void registerUser(
			@Valid @RequestBody(required = true) UserRegistrationMessage userRegistrationData) {
		userService.registerUser(userRegistrationData);
	}
	
	@GetMapping("/{username}")
	@JsonView(UserPresentationView.FullProfile.class)
	public User getUserByUsername(@PathVariable(required = true) String username) {
		return userService.getUserByUsername(username);
	}
	
	private void checkPrincipalWithUsername(
			String username, Principal principal, String exceptionText) {
		if(!principal.getName().equals(username)) {
			throw new ResourceOperationException(exceptionText);
		}
	}
	
	@GetMapping("/{username}/grades")
	@JsonView(GradePresentationView.UserReceivedGradesView.class)
	@PreAuthorize("isAuthenticated()")
	public List<GradeHolder> getUserGrades(
			@PathVariable(required = true) String username,
			Principal principal) {
		checkPrincipalWithUsername(
				username, principal, "User can show only their own grades");
		
		User user = userService.getUserByUsername(username);
		return gradeService.getFinishedGradesForUser(user);
	}
	
	@GetMapping("/{username}/grades/waiting")
	@JsonView(GradePresentationView.IncompletedGradesView.class)
	@PreAuthorize("isAuthenticated()")
	public List<GradeHolder> getWaitingGrades(
			@PathVariable(required = true) String username,
			Principal principal) {
		checkPrincipalWithUsername(
				username, principal, "User can show only their own grades");
		User user = userService.getUserByUsername(username);
		return gradeService.getWaitingGradesForGivingUser(user);
	}
	
}
