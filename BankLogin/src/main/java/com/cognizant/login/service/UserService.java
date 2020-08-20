package com.cognizant.login.service;

import java.util.Optional;

import com.cognizant.login.model.User;

public interface UserService {
	
	User createUser(User user);
	Optional<User> getById(int id);
	public void updateUserById(User user);
	public void deleteUserById(int id);
	User findByUserName(String userName);
}
