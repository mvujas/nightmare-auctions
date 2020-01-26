package com.github.mvujas.nightmareauctionsbackend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.github.mvujas.nightmareauctionsbackend.exceptionhandling.exceptions.ResourceOperationException;
import com.github.mvujas.nightmareauctionsbackend.model.User;
import com.github.mvujas.nightmareauctionsbackend.repositories.PrivateMessageRepository;
import com.github.mvujas.nightmareauctionsbackend.repositories.UserRepository;

@Service
public class PrivateMessageService {

	@Autowired
	private PrivateMessageRepository privateMessageRepository;
	@Autowired
	private UserRepository userRepository;
	

	public List<User> getChattersOrderedByDateForUser(String username) {
		User user = userRepository.findByUsername(username);
		
		if(user == null) {
			throw new ResourceOperationException("There is no user under given username");
		}
		
		return privateMessageRepository.getChattersOrderedByDateForUser(user);
	}
	

}
