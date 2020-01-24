package com.github.mvujas.nightmareauctionsbackend.controllers.messages;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.github.mvujas.nightmareauctionsbackend.validation.annotations.ExistantCategoryName;

public class ItemCreationMessage {

	@NotBlank(message = "Item name cannot be empty")
	private String name;
	
	@Min(
		value = 1, 
		message = "Item must cost at least $1")
	private int startingPrice;
	
	@NotNull(message = "Category cannot be null")
	@ExistantCategoryName(
		message = "There is no category with given name")
	private String categoryName;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getStartingPrice() {
		return startingPrice;
	}

	public void setStartingPrice(Integer startingPrice) {
		this.startingPrice = startingPrice;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

}
