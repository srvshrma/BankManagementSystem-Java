package com.cognizant.account.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.account.dao.AccountRepository;
import com.cognizant.account.model.Account;
@Service
public class AccountServiceImpl implements AccountService {
@Autowired
AccountRepository repo;
	@Override
	public Account createAccount(Account account) {
		// TODO Auto-generated method stub
		return repo.save(account);
	}

	@Override
	public Optional<Account> getById(int id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}

	@Override
	public Account findByAccountNo(long accountNo) {
		// TODO Auto-generated method stub
		return repo.findByAccountNo(accountNo);
	}

	@Override
	public void updateAccountById(Account account) {
		// TODO Auto-generated method stub
		repo.save(account);
	}

	@Override
	public void deleteAccountById(int id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
	}

}
