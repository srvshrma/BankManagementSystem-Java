package com.cognizant.fund.service;

import java.util.Optional;

import com.cognizant.fund.model.MutualFund;

public interface MutualFundService {
	
	MutualFund createFund(MutualFund mutualFund);
	Optional<MutualFund> findById(int id);

}
