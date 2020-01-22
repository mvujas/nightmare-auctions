package com.github.mvujas.nightmareauctionsbackend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity(name = "item")
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(unique = true, nullable = false)
	private String name;
	
	@Column(nullable = false)
	private int startingPrice;

	@ManyToOne(optional = false)
    @JsonManagedReference
	private Category category;

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

	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", startingPrice=" + startingPrice + ", category=" + category
				+ "]";
	}
	
}
