package com.cognizant.fund.model;

import java.time.LocalDate;
import java.util.Optional;

public class Dto {
	private int id;
	   
    private String fundId;
    private String name;
	private double amountToInvest;
	private LocalDate timeStamp;
	private Optional<Account> account;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public double getAmountToInvest() {
		return amountToInvest;
	}
	public void setAmountToInvest(double amountToInvest) {
		this.amountToInvest = amountToInvest;
	}
	public LocalDate getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(LocalDate timeStamp) {
		this.timeStamp = timeStamp;
	}
	public Optional<Account> getAccount() {
		return account;
	}
	public void setAccount(Optional<Account> account) {
		this.account = account;
	}
	
	
}
