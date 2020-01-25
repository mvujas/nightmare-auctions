package com.github.mvujas.nightmareauctionsbackend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;

import com.fasterxml.jackson.annotation.JsonView;
import com.github.mvujas.nightmareauctionsbackend.presentationview.GradePresentationView;

@Entity
@Table(name = "grade_holder")
public class GradeHolder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	private Integer value;
	
	@ManyToOne(optional = false)
	private User givingGrade;

	@ManyToOne(optional = false)
	private User receivingGrade;
	
	@Formula(
			"(SELECT g.id " // This is same as its item id
			+ "FROM grade g "
			+ "WHERE g.author_grade_id = id OR g.buyer_grade_id = id)")
	private int itemId;


	@JsonView(GradePresentationView.Identifier.class)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@JsonView(GradePresentationView.CompleteGrade.class)
	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	@JsonView(GradePresentationView.ReceivedGrade.class)
	public User getGivingGrade() {
		return givingGrade;
	}

	public void setGivingGrade(User givingGrade) {
		this.givingGrade = givingGrade;
	}

	@JsonView(GradePresentationView.GivenGrade.class)
	public User getReceivingGrade() {
		return receivingGrade;
	}

	public void setReceivingGrade(User receivingGrade) {
		this.receivingGrade = receivingGrade;
	}

	@JsonView(GradePresentationView.ItemInfo.class)
	public int getItemId() {
		return itemId;
	}

}
