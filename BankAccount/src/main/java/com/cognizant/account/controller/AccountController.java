package com.cognizant.account.controller;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.account.model.Account;
import com.cognizant.account.model.AccountData;
import com.cognizant.account.response.Response;
import com.cognizant.account.service.AccountService;
import com.cognizant.account.validation.Validate;

@RestController
public class AccountController {
	
	@Autowired
	AccountService service;
	
	public boolean checkspecialcharacter(String data) {
		Pattern special = Pattern.compile("[!#$%&*()_+=|<>?{}\\[\\]~-]");

		Matcher value = special.matcher(data);
		if (value.find())
			return true;
		return false;
	}
	@PostMapping("/Create-Account")
	public ResponseEntity<Response> CreateAccount(
			@RequestBody AccountData data) {

		Response response = new Response();
		HttpStatus status = HttpStatus.OK;
		DecimalFormat D_F = new DecimalFormat("0.00");
		
			Account acc = new Account();

//			 check for blank data
			if (Validate.check(data.getBankName())) {
				status = HttpStatus.BAD_REQUEST;
				response.setMessage("Bank Name can't be blank");
				return new ResponseEntity<Response>(response, status);
			}

			if (Validate.check(data.getIfsc())) {
				status = HttpStatus.BAD_REQUEST;
				response.setMessage("Ifsc can't be blank");
				return new ResponseEntity<Response>(response, status);
			}
			
			if(data.getAmount()<=0 ) 
			{
				status = HttpStatus.BAD_REQUEST;
				response.setMessage("Amount cant be 0");
				return new ResponseEntity<Response>(response, status);
			}
			
//			 check for special character
				if (checkspecialcharacter(data.getBankName())) {
					status = HttpStatus.BAD_REQUEST;
					response.setMessage("Bank Name Contain Special characters");
					return new ResponseEntity<Response>(response, status);
				}

				if (checkspecialcharacter(data.getIfsc())) {
					status = HttpStatus.BAD_REQUEST;
					response.setMessage("Ifcs code Contain Special characters");
					return new ResponseEntity<Response>(response, status);
				}
			
				
// 			Account No validation 
			try {
				Account accnt = service.findByAccountNo(data.getAccountNo());
				if (accnt != null) {
					status = HttpStatus.CONFLICT;
					response.setMessage("Account already associated with different users");
					return new ResponseEntity<Response>(response, status);
				}
			} catch (Exception e) {
				System.out.println("unique account no");
			}

			try {
				String len = String.valueOf(data.getAccountNo());
				if(len.length()!=10)
				{
					status = HttpStatus.UNPROCESSABLE_ENTITY;
					response.setMessage("Account no is invalid, Please check and try again");
					return new ResponseEntity<Response>(response, status);
				}
			}
			catch(Exception e) {}
			
		
			D_F.setRoundingMode(RoundingMode.UP);
			float amount=Float.parseFloat(D_F.format((data.getAmount()*1.00)));
			
			acc.setAccountNo(data.getAccountNo());
			acc.setBankName(data.getBankName());
			acc.setIfsc(data.getIfsc());
			acc.setMicrCode(data.getMicrCode());
			acc.setAmount(amount);
				
			service.createAccount(acc);

			response.setMessage("Account created successfully");
		

		return new ResponseEntity<Response>(response, status);
	}
	
	@GetMapping("id/{id}")
	Optional<Account> findById(@PathVariable(value = "id")int id)
	{
		return service.getById(id);
	}
	@GetMapping("accountno/{no}")
	Account findByAccountNo(@PathVariable(value = "no")long accountNo)
	{
		return service.findByAccountNo(accountNo);
	}
	@PutMapping("/updateaccount/{id}")
	public void updateById(@RequestBody Account account,@PathVariable(value = "id") int id) {
		Account update = new Account(id,account.getAccountNo(), account.getIfsc(), account.getBankName(), account.getMicrCode(), account.getAmount());
		service.updateAccountById(update);
	}
	@DeleteMapping("/deleteaccount/{id}")
	public void deleteById(@PathVariable(value = "id") int id) {
		service.deleteAccountById(id);
	}
}
