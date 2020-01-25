package com.github.mvujas.nightmareauctionsbackend.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Grade")
public class Grade {

	@Id
	private int id;
	
	@OneToOne
	@JoinColumn(name = "id")
	@MapsId
	private Item item;

	@OneToOne
	private Bid bid;

	private Integer gradeForAuthor;
	private Integer gradeForBuyer;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Bid getBid() {
		return bid;
	}

	public void setBid(Bid bid) {
		this.bid = bid;
	}

	public Integer getGradeForAuthor() {
		return gradeForAuthor;
	}

	public void setGradeForAuthor(Integer gradeForAuthor) {
		this.gradeForAuthor = gradeForAuthor;
	}

	public Integer getGradeForBuyer() {
		return gradeForBuyer;
	}

	public void setGradeForBuyer(Integer gradeForBuyer) {
		this.gradeForBuyer = gradeForBuyer;
	}
	
}
