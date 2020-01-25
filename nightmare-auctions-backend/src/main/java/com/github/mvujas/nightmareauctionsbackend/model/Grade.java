package com.github.mvujas.nightmareauctionsbackend.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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

	@OneToOne(optional = false, cascade = CascadeType.ALL)
	private GradeHolder authorGrade;

	@OneToOne(optional = false, cascade = CascadeType.ALL)
	private GradeHolder buyerGrade;
	
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

	public GradeHolder getAuthorGrade() {
		return authorGrade;
	}

	public void setAuthorGrade(GradeHolder authorGrade) {
		this.authorGrade = authorGrade;
	}

	public GradeHolder getBuyerGrade() {
		return buyerGrade;
	}

	public void setBuyerGrade(GradeHolder buyerGrade) {
		this.buyerGrade = buyerGrade;
	}
	
}
