package com.github.mvujas.nightmareauctionsbackend.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.github.mvujas.nightmareauctionsbackend.util.TimeUtils;

@Entity
@Table(name = "bid")
public class Bid {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(updatable = false, nullable = false)
	private int price;

	@ManyToOne(optional = false)
	private User author;
	
	@Column(updatable = false, nullable = false)
	private Timestamp postingTime;
	
	@ManyToOne(optional = false)
	private Item item;
	
	
	@PrePersist
	protected void onCreate() {
		postingTime = TimeUtils.getCurrentTimestamp();
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public Timestamp getPostingTime() {
		return postingTime;
	}

	public void setPostingTime(Timestamp postingTime) {
		this.postingTime = postingTime;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

}
