package com.cognizant.fund.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cognizant.fund.api.FeignClient;
import com.cognizant.fund.dao.MutualFundRepository;
import com.cognizant.fund.model.Account;
import com.cognizant.fund.model.MutualFund;
@Service
public class MutualFundServiceImpl implements MutualFundService {
@Autowired
MutualFundRepository repo;

@Autowired
FeignClient feign;
	@Override
	public MutualFund createFund(MutualFund mutualFund) {
		// TODO Auto-generated method stub
		return repo.save(mutualFund);
	}

	@Override
	public Optional<MutualFund> findById(int id) {
		// TODO Auto-generated method stub
		
		return repo.findById(id);
	}

}
