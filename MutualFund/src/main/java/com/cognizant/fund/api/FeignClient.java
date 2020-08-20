package com.cognizant.fund.api;

import java.util.Optional;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cognizant.fund.model.Account;

@org.springframework.cloud.openfeign.FeignClient(value="account-service",url = "http://localhost:8081/",fallback = AccountFallBack.class)
public interface FeignClient {
	
	@LoadBalanced
	@RequestMapping(value = "/id/{id}",method = RequestMethod.GET)
	public Optional<Account> findById(@PathVariable(value = "id")int id);

}
