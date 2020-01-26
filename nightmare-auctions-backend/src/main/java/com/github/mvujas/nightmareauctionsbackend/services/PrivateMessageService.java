package com.github.mvujas.nightmareauctionsbackend.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.mvujas.nightmareauctionsbackend.exceptionhandling.exceptions.ResourceOperationException;
import com.github.mvujas.nightmareauctionsbackend.model.PrivateMessage;
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
	
	public void saveMessage(String senderUsername, String receiverUsername, String text) {
		if(senderUsername.equals(receiverUsername)) {
			throw new ResourceOperationException(
					"User can't send messages to himself");
		}
		
		text = text.trim();
		
		User sender = userRepository.findByUsername(senderUsername);
		if(sender == null) {
			throw new ResourceOperationException(
					"There is no user under sender's provided information");
		}
		User receiver = userRepository.findByUsername(receiverUsername);
		if(receiver == null) {
			throw new ResourceOperationException(
					"There is no user under receiver's provided information");
		}
		
		PrivateMessage privateMessage = new PrivateMessage();
		privateMessage.setSender(sender);
		privateMessage.setReceiver(receiver);
		privateMessage.setText(text);
		privateMessageRepository.saveAndFlush(privateMessage);
	}
	
	public List<PrivateMessage> getMessagesBetweenUsersSince(
			String requesterUsername, String otherUserUsername, Date date) {
		User requester = userRepository.findByUsername(requesterUsername);
		if(requester == null) {
			throw new ResourceOperationException(
					"There is no user under requester's provided information");
		}
		User otherUser = userRepository.findByUsername(otherUserUsername);
		if(otherUser == null) {
			throw new ResourceOperationException(
					"There is no user under other user's provided information");
		}
		
		return privateMessageRepository
				.getMessagesBetweenUsersSinceDate(requester, otherUser, date);
	}
	

}
