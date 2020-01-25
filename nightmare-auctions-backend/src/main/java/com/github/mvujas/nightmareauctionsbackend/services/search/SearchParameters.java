package com.github.mvujas.nightmareauctionsbackend.services.search;

public class SearchParameters {

	private String name;
	private String categoryName;
	private Integer minimumPrice, maximumPrice;
	private Boolean isOver;
	
	public SearchParameters() {
		super();
	}
	
	public SearchParameters(String name, String categoryName, Integer minimumPrice, Integer maximumPrice,
			Boolean isOver) {
		super();
		this.name = name;
		this.categoryName = categoryName;
		this.minimumPrice = minimumPrice;
		this.maximumPrice = maximumPrice;
		this.isOver = isOver;
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
	public Boolean isOver() {
		return isOver;
	}
	public void setOver(Boolean isOver) {
		this.isOver = isOver;
	}

	@Override
	public String toString() {
		return "SearchParameters [name=" + name + ", categoryName=" + categoryName + ", minimumPrice=" + minimumPrice
				+ ", maximumPrice=" + maximumPrice + ", isOver=" + isOver + "]";
	}
	
}
