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

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.github.mvujas.nightmareauctionsbackend.presentationview.ItemPresentationView;
import com.github.mvujas.nightmareauctionsbackend.util.TimeUtils;

@Entity
@Table(name = "item")
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(ItemPresentationView.SummaryView.class)
	private int id;
	
	@Column(unique = true, nullable = false)
	@JsonView(ItemPresentationView.SummaryView.class)
	private String name;
	
	@Column(nullable = false)
	@JsonView(ItemPresentationView.SummaryView.class)
	private int startingPrice;
	
	@Column(nullable = false, updatable = false)
	@JsonView(ItemPresentationView.SummaryView.class)
	private Timestamp postingTime;
	
	@ManyToOne(optional = false)
    @JsonManagedReference
	@JsonView(ItemPresentationView.SummaryView.class)
	private User author;

	@ManyToOne(optional = false)
    @JsonManagedReference
	@JsonView(ItemPresentationView.SummaryView.class)
	private Category category;

	
	@PrePersist
	protected void onCreate() {
		postingTime = TimeUtils.getCurrentTimestamp();
	}
	
	
	public Item(String name, int startingPrice, Category category) {
		super();
		this.name = name;
		this.startingPrice = startingPrice;
		this.category = category;
	}

	public Item() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStartingPrice() {
		return startingPrice;
	}

	public void setStartingPrice(int startingPrice) {
		this.startingPrice = startingPrice;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	public Timestamp getPostingTime() {
		return postingTime;
	}

	public void setPostingTime(Timestamp postingTime) {
		this.postingTime = postingTime;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}


	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", startingPrice=" + startingPrice + ", category=" + category
				+ "]";
	}
	
}
