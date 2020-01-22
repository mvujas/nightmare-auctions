package com.github.mvujas.nightmareauctionsbackend.services.search;

public class SearchParameters {

	private String name;
	private String categoryName;
	private Integer minimumPrice, maximumPrice;
	
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
	
	@Override
	public String toString() {
		return "SearchParameters [name=" + name + ", categoryName=" + categoryName + ", minimumPrice=" + minimumPrice
				+ ", maximumPrice=" + maximumPrice + "]";
	}
	
}
