package com.cognizant.login.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.cognizant.login.model.User;
@Repository
@EnableTransactionManagement
public interface UserRepository extends JpaRepository<User, Integer> {
	User findByUserName(String userName);
}
