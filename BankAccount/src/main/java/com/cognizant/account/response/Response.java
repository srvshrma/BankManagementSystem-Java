package com.cognizant.account.response;

import java.util.List;

import com.cognizant.account.model.Account;

public class Response {
	
	public String message;
	public List<Account> lst;
	

	
	
	public List<Account> getLst() {
		return lst;
	}

	public void setLst(List<Account> lst) {
		this.lst = lst;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


}
