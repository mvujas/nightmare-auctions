package com.github.mvujas.nightmareauctionsbackend.services.search;

import java.util.Date;

public class SearchParameters {

	private String name;
	private String categoryName;
	private String username;
	private Integer minimumPrice, maximumPrice;
	private Boolean isOver;
	private Date before;
	private Date after;
	
	public SearchParameters() {
		super();
	}

	public SearchParameters(String name, String categoryName, String username, Integer minimumPrice,
			Integer maximumPrice, Boolean isOver, Date before, Date after) {
		super();
		this.name = name;
		this.categoryName = categoryName;
		this.username = username;
		this.minimumPrice = minimumPrice;
		this.maximumPrice = maximumPrice;
		this.isOver = isOver;
		this.before = before;
		this.after = after;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getMinimumPrice() {
		return minimumPrice;
	}
	public void setMinimumPrice(Integer minimumPrice) {
		this.minimumPrice = minimumPrice;
	}
	public Integer getMaximumPrice() {
		return maximumPrice;
	}
	public void setMaximumPrice(Integer maximumPrice) {
		this.maximumPrice = maximumPrice;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Boolean isOver() {
		return isOver;
	}
	public void setOver(Boolean isOver) {
		this.isOver = isOver;
	}
	public Date getBefore() {
		return before;
	}
	public void setBefore(Date before) {
		this.before = before;
	}
	public Date getAfter() {
		return after;
	}
	public void setAfter(Date after) {
		this.after = after;
	}

	@Override
	public String toString() {
		return "SearchParameters [name=" + name + ", categoryName=" + categoryName + ", username=" + username
				+ ", minimumPrice=" + minimumPrice + ", maximumPrice=" + maximumPrice + ", isOver=" + isOver
				+ ", before=" + before + ", after=" + after + "]";
	}
	
}
