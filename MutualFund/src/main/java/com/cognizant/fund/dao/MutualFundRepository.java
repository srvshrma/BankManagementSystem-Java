package com.cognizant.fund.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.cognizant.fund.model.MutualFund;
@Repository
@EnableTransactionManagement
public interface MutualFundRepository extends JpaRepository<MutualFund, Integer> {

}
