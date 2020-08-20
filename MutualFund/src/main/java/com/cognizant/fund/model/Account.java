package com.cognizant.fund.model;

public class Account {
	private int id;
	private long accountNo;
	
	private String ifsc;

	private String bankName;

	private long micrCode;
	
	private float amount;

	public Account(int id, long accountNo, String ifsc, String bankName, long micrCode, float amount) {
		super();
		this.id = id;
		this.accountNo = accountNo;
		this.ifsc = ifsc;
		this.bankName = bankName;
		this.micrCode = micrCode;
		this.amount = amount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(long accountNo) {
		this.accountNo = accountNo;
	}

	public String getIfsc() {
		return ifsc;
	}

	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public long getMicrCode() {
		return micrCode;
	}

	public void setMicrCode(long micrCode) {
		this.micrCode = micrCode;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}
	
	
}
