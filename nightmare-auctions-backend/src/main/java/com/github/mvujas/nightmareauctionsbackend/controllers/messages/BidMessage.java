package com.github.mvujas.nightmareauctionsbackend.controllers.messages;

import javax.validation.constraints.NotNull;

public class BidMessage {

	@NotNull(
		message = "Bid price is required")
	private Integer price;

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}
	
}
