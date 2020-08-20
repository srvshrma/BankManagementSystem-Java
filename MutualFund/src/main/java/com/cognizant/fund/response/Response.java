package com.cognizant.fund.response;

import java.util.List;
import java.util.Optional;


import com.cognizant.fund.model.MutualFund;

public class Response {
	
	public String mgs;
	public List<MutualFund> list;
	public Optional<MutualFund> byid;
	public String getMgs() {
		return mgs;
	}
	public void setMgs(String mgs) {
		this.mgs = mgs;
	}
	public List<MutualFund> getList() {
		return list;
	}
	public void setList(List<MutualFund> list) {
		this.list = list;
	}
	public Optional<MutualFund> getByid() {
		return byid;
	}
	public void setByid(Optional<MutualFund> byid) {
		this.byid = byid;
	}  

	
	

}
