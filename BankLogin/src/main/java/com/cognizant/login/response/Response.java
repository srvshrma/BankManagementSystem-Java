package com.cognizant.login.response;

import java.util.List;

import com.cognizant.login.model.User;

public class Response {

	 
	 public String message;
	 public String token;
	 public User usr;
	 public List<User> list;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public User getUsr() {
		return usr;
	}
	public void setUsr(User usr) {
		this.usr = usr;
	}
	public List<User> getList() {
		return list;
	}
	public void setList(List<User> list) {
		this.list = list;
	}
	 
	 
	
	
	

	 
	 
}

