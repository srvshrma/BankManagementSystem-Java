package com.cognizant.login.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.login.dao.UserRepository;
import com.cognizant.login.model.User;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository repo;
	@Override
	public User createUser(User user) {
		// TODO Auto-generated method stub
		return repo.save(user);
	}

	@Override
	public Optional<User> getById(int id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}

	@Override
	public void updateUserById(User user) {
		// TODO Auto-generated method stub
		repo.save(user);
	}

	@Override
	public void deleteUserById(int id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
	}

	@Override
	public User findByUserName(String userName) {
		// TODO Auto-generated method stub
		return repo.findByUserName(userName);
	}

}
