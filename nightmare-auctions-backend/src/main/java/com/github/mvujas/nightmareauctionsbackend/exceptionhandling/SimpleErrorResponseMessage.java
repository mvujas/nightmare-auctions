package com.github.mvujas.nightmareauctionsbackend.exceptionhandling;

class SimpleErrorResponseMessage {

	private String message;
	
	public SimpleErrorResponseMessage() {
		super();
	}

	public SimpleErrorResponseMessage(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	

}
