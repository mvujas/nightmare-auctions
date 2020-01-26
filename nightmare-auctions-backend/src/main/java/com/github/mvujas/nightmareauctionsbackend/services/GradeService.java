package com.github.mvujas.nightmareauctionsbackend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.mvujas.nightmareauctionsbackend.exceptionhandling.exceptions.ResourceOperationException;
import com.github.mvujas.nightmareauctionsbackend.model.GradeHolder;
import com.github.mvujas.nightmareauctionsbackend.model.User;
import com.github.mvujas.nightmareauctionsbackend.repositories.GradeHolderRepository;

@Service
public class GradeService {

	@Autowired
	private GradeHolderRepository gradeHolderRepository;
	
	public List<GradeHolder> getFinishedGradesForUser(User user) {
		return gradeHolderRepository.getFinishedGradesForUser(user);
	}
	
	public List<GradeHolder> getWaitingGradesForGivingUser(User user) {
		return gradeHolderRepository.getWaitingGradesForGivingUser(user);
	}
	
	public void saveGradeHolderValue(
			String usernameOfGiver, int gradeHolderId, int gradeHolderValue) {
		GradeHolder gradeHolder = 
				gradeHolderRepository.findById(gradeHolderId).orElse(null);
		if(gradeHolder == null) {
			throw new ResourceOperationException(
					"There is no grade under given id");
		}
		if(!gradeHolder.getGivingGrade().getUsername().equals(usernameOfGiver)) {
			throw new ResourceOperationException(
					"You can't give this grade");
		}
		if(gradeHolder.getValue() != null) {
			throw new ResourceOperationException(
					"Grade already has a value");
		}
		gradeHolder.setValue(gradeHolderValue);
		
		gradeHolderRepository.saveAndFlush(gradeHolder);
	}

}
