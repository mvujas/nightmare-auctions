package com.github.mvujas.nightmareauctionsbackend.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Formula;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.github.mvujas.nightmareauctionsbackend.presentationview.ItemPresentationView;
import com.github.mvujas.nightmareauctionsbackend.util.TimeUtils;

@Entity
@Table(name = "item")
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(unique = true, nullable = false)
	private String name;
	
	@Column(nullable = false)
	private int startingPrice;
	
	@Column(nullable = false, updatable = false)
	private Timestamp postingTime;
	
	@ManyToOne(optional = false)
	private User author;

	@ManyToOne(optional = false)
	private Category category;
	
	@OneToMany(mappedBy = "item")
	private List<Bid> bids = new ArrayList<>();
	
	@PrePersist
	protected void onCreate() {
		postingTime = TimeUtils.getCurrentTimestamp();
	}

	@Formula(
			"(SELECT COUNT(b.id) "
			+ "FROM bid b "
			+ "WHERE b.item_id = id)")
	private Integer numberOfBids = 1;
	
	@Formula(
			"(SELECT IFNULL(MAX(b.price), starting_price) "
			+ "FROM bid b "
			+ "WHERE b.item_id = id)")
	private Integer price = 1;
	
	public Item(String name, int startingPrice, Category category) {
		super();
		this.name = name;
		this.startingPrice = startingPrice;
		this.category = category;
	}

	public Item() {
		super();
	}

	@JsonView(ItemPresentationView.SummaryView.class)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@JsonView(ItemPresentationView.SummaryView.class)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonView(ItemPresentationView.FullView.class)
	public int getStartingPrice() {
		return startingPrice;
	}

	public void setStartingPrice(int startingPrice) {
		this.startingPrice = startingPrice;
	}

    @JsonManagedReference
	@JsonView(ItemPresentationView.SummaryView.class)
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	@JsonView(ItemPresentationView.SummaryView.class)
	public Timestamp getPostingTime() {
		return postingTime;
	}

	public void setPostingTime(Timestamp postingTime) {
		this.postingTime = postingTime;
	}

	@JsonView(ItemPresentationView.SummaryView.class)
    @JsonManagedReference
	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	@JsonView(ItemPresentationView.FullView.class)
	public List<Bid> getBids() {
		return bids;
	}

	public void setBids(List<Bid> bids) {
		this.bids = bids;
		numberOfBids = null;
		price = null;
	}

	@JsonView(ItemPresentationView.SummaryView.class)
	public int getPrice() {
		return price;
	}

	@JsonView(ItemPresentationView.SummaryView.class)
	public int getNumberOfBids() {
		return numberOfBids;
	}


	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", startingPrice=" + startingPrice + ", category=" + category
				+ "]";
	}
	
}
