package com.cognizant.fund.model;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.ToString;
@Entity
@ToString
public class MutualFund {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
   
    private String fundId;
    private String name;
	@Column(name = "amount_to_invest")
	private double amountToInvest;
	@Column(name = "time_stamp")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	@JsonFormat(pattern = "MM/dd/yyyy")
	private LocalDate timeStamp;
	
	public LocalDate getTimeStamp() {
		return timeStamp.now();
	}
	public void setTimeStamp(LocalDate timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getFundId() {
		return fundId;
	}
	public void setFundId(String fundId) {
		this.fundId = fundId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public MutualFund(int id, String fundId, String name, double amountToInvest, LocalDate timeStamp) {
		super();
		this.id = id;
		this.fundId = fundId;
		this.name = name;
		this.amountToInvest = amountToInvest;
		this.timeStamp = timeStamp;
	}
	public MutualFund() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getAmountToInvest() {
		return amountToInvest;
	}
	public void setAmountToInvest(double amountToInvest) {
		this.amountToInvest = amountToInvest;
	}
	
	
}
