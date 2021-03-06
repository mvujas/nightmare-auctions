package com.github.mvujas.nightmareauctionsbackend.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

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
	
	@Column(columnDefinition="TEXT", nullable = false)
	private String details;
	
	@Column(nullable = false)
	private int startingPrice;
	
	@Column(nullable = false, updatable = false)
	private Timestamp postingTime;
	
	@Column(nullable = true)
	private Timestamp closingTime;
	
	@ManyToOne(optional = false)
	private User author;

	@ManyToOne(optional = false)
	private Category category;
	
	@OneToMany(mappedBy = "item")
	private List<Bid> bids;
	
	@OneToOne(mappedBy = "item")
	private Grade grade;
	
	
	@PrePersist
	protected void onCreate() {
		postingTime = TimeUtils.getCurrentTimestamp();
	}

	
	@Formula(
			"(SELECT COUNT(b.id) "
			+ "FROM bid b "
			+ "WHERE b.item_id = id)")
	private Integer numberOfBids;

	// Works on MariaDB, it's questionable whether it works on other DBMSs
	@Formula(
			"(SELECT IFNULL(MAX(b.price), starting_price) "
			+ "FROM bid b "
			+ "WHERE b.item_id = id)")
	private Integer price;
	
	@Formula(
			"(SELECT u.username "
			+ "FROM grade g, bid b, user u "
			+ "WHERE g.id = id AND b.id = g.bid_id AND b.author_id = u.id)")
	private String buyerUsername;
	
	@Formula("NOT ISNULL(closing_time)")
	private boolean over;
	
	public Item(String name, int startingPrice, Category category) {
		super();
		this.name = name;
		this.startingPrice = startingPrice;
		this.category = category;
	}

	public Item() {
		super();
	}

	@JsonView(ItemPresentationView.Identifier.class)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@JsonView(ItemPresentationView.Name.class)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonView(ItemPresentationView.StartingPrice.class)
	public int getStartingPrice() {
		return startingPrice;
	}

	public void setStartingPrice(int startingPrice) {
		this.startingPrice = startingPrice;
	}

    @JsonManagedReference
	@JsonView(ItemPresentationView.Category.class)
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@JsonView(ItemPresentationView.PostingTime.class)
	public Timestamp getPostingTime() {
		return postingTime;
	}

	public void setPostingTime(Timestamp postingTime) {
		this.postingTime = postingTime;
	}

	@JsonView(ItemPresentationView.Author.class)
    @JsonManagedReference
	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	@JsonView(ItemPresentationView.Bids.class)
	public List<Bid> getBids() {
		return bids;
	}

	public void setBids(List<Bid> bids) {
		this.bids = bids;
		numberOfBids = null;
		price = null;
	}

	@JsonView(ItemPresentationView.Price.class)
	public int getPrice() {
		return price;
	}

	@JsonView(ItemPresentationView.NumberOfBids.class)
	public int getNumberOfBids() {
		return numberOfBids;
	}

	@JsonView(ItemPresentationView.Over.class)
	public boolean isOver() {
		return over;
	}
	
	public void endAuction() {
		if(closingTime == null) {
			closingTime = TimeUtils.getCurrentTimestamp();
		}
	}

	@JsonView(ItemPresentationView.ClosingTime.class)
	public Timestamp getClosingTime() {
		return closingTime;
	}

	public void setClosingTime(Timestamp closingTime) {
		this.closingTime = closingTime;
	}

	@JsonView(ItemPresentationView.Grade.class)
	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	@JsonView(ItemPresentationView.Details.class)
	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}
	
	public String getBuyerUsername() {
		return buyerUsername;
	}
	
	
	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", startingPrice=" + startingPrice + ", category=" + category
				+ "]";
	}
	
}
