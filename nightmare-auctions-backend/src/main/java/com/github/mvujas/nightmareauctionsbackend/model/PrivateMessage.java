package com.github.mvujas.nightmareauctionsbackend.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import com.github.mvujas.nightmareauctionsbackend.util.TimeUtils;

@Entity(name = "private_message")
public class PrivateMessage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false)
	private String text;
	
	@Column(nullable = false, updatable = false)
	private Timestamp sendingTime;
	
	@ManyToOne
	@JoinColumn(name = "sender_id", updatable = false, nullable = false)
	private User sender;
	
	@ManyToOne
	@JoinColumn(name = "receiver_id", updatable = false, nullable = false)
	private User receiver;
	
	
	@PrePersist
	protected void onCreate() {
		sendingTime = TimeUtils.getCurrentTimestamp();
	}
	

	public PrivateMessage() {
		super();
	}

	public PrivateMessage(String text, Timestamp sendingTime, User sender, User receiver) {
		super();
		this.text = text;
		this.sendingTime = sendingTime;
		this.sender = sender;
		this.receiver = receiver;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Timestamp getSendingTime() {
		return sendingTime;
	}

	public void setSendingTime(Timestamp sendingTime) {
		this.sendingTime = sendingTime;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}

}
