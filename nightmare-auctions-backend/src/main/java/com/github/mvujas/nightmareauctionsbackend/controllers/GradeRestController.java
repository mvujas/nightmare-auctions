package com.github.mvujas.nightmareauctionsbackend.controllers;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.mvujas.nightmareauctionsbackend.controllers.messages.GiveGradeMessage;
import com.github.mvujas.nightmareauctionsbackend.services.GradeService;

@RestController
@RequestMapping("${api.url}/grade")
public class GradeRestController {

	@Autowired
	private GradeService gradeService;
	
	@PostMapping("/{gradeHolderId}")
	@PreAuthorize("isAuthenticated()")
	public void saveGradeHolder(
			@PathVariable(required = true) int gradeHolderId,
			@Valid @RequestBody GiveGradeMessage gradeValueMessage,
			Principal principal) {
		gradeService.saveGradeHolderValue(
				principal.getName(), 
				gradeHolderId, 
				gradeValueMessage.getValue());
	}

}
