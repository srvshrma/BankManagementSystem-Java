package com.cognizant.fund.api;

import java.util.Optional;

import org.springframework.stereotype.Component;
import com.cognizant.fund.model.Account;

@Component
public class AccountFallBack implements FeignClient{
	
	public Optional<Account> findById(int id)
	{
		Optional<Account> account = Optional.of(new Account(0, 00, "down", "down", 00, 00));
		return account;
	}
}
