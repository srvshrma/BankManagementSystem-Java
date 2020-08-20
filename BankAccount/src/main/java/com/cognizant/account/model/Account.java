package com.cognizant.account.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.ToString;

@Entity
@ToString
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "acc_no")
	private long accountNo;
	@Column(name = "ifsc_code")
	private String ifsc;
	@Column(name = "bank_name")
	private String bankName;
	@Column(name = "micr_code")
	private long micrCode;
	
	private float amount;
	
	

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public Account(int id, long accountNo, String ifsc, String bankName, long micrCode, float amount) {
		super();
		this.id = id;
		this.accountNo = accountNo;
		this.ifsc = ifsc;
		this.bankName = bankName;
		this.micrCode = micrCode;
		this.amount = amount;
	}

	public Account() {
		super();
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
	

}
