package com.github.mvujas.nightmareauctionsbackend.controllers.messages;

import javax.validation.constraints.NotBlank;

public class PrivateMessageMessageHolder {

	@NotBlank(message = "Text of private message cannot be empty")
	private String text;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
