package com.github.mvujas.nightmareauctionsbackend.domain;

import java.sql.Timestamp;

import com.github.mvujas.nightmareauctionsbackend.model.Item;

public class SoldItem {

	private int itemId;
	private String name;
	private Timestamp closingTimestamp;
	private String buyer;
	
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Timestamp getClosingTimestamp() {
		return closingTimestamp;
	}
	public void setClosingTimestamp(Timestamp closingTimestamp) {
		this.closingTimestamp = closingTimestamp;
	}
	public String getBuyer() {
		return buyer;
	}
	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}
	
	public static SoldItem fromItem(Item item) {
		SoldItem soldItem = new SoldItem();
		soldItem.setBuyer(item.getBuyerUsername());
		soldItem.setItemId(item.getId());
		soldItem.setName(item.getName());
		soldItem.setClosingTimestamp(item.getClosingTime());
		return soldItem;
	}
	

}
