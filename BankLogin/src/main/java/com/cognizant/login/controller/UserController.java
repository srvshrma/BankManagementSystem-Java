package com.cognizant.login.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.login.jwt.JwtRequest;
import com.cognizant.login.jwt.JwtResponse;
import com.cognizant.login.jwt.JwtTokenUtil;
import com.cognizant.login.model.Login;
import com.cognizant.login.model.User;
import com.cognizant.login.response.Response;
import com.cognizant.login.service.JwtUserDetailsService;
import com.cognizant.login.service.UserService;
import com.cognizant.login.validation.ValidateData;

@RestController
public class UserController {
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	UserService service;
	@Autowired
	public BCryptPasswordEncoder passwordEncoder;
	
	Logger logger = LoggerFactory.getLogger(UserController.class);
	
	public boolean checkspecialcharacter(String data) 
	{
		Pattern special = Pattern.compile ("[!#$%&*()_+=|<>?{}\\[\\]~-]");
		
		Matcher value = special.matcher(data);
		if(value.find()) 
			return true;
		return false;
	}
	
	public boolean panformatvalidation(String pancarddata) 
	{
		String regex = "[A-Z]{5}[0-9]{4}[A-Z]{1}";
		
		Pattern pattern  = Pattern.compile(regex);
		
		if(pancarddata==null)
			return false;
		
		Matcher matcher = pattern.matcher(pancarddata);
		
		return matcher.matches();
	}
	
	public boolean emailformatvalidation(String emaildata) 
	{
		String emailformat = "^[\\w-\\+]*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
		
		Pattern pattern = Pattern.compile(emailformat);
		
		if(emaildata==null)
			return false;
		Matcher match = pattern.matcher(emaildata);
		
		return match.matches();
	}
	

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<Response> RegisterUser(@RequestBody User myUser) {

		HttpStatus status = HttpStatus.CREATED;
		Response response = new Response();

		try {
			
//			check for blank data
			
			if(ValidateData.check(myUser.getEmail())) {
				status = HttpStatus.BAD_REQUEST;
				response.setMessage("Email can't be blank");
				return new ResponseEntity<Response>(response, status);
			}
			
			if(ValidateData.check(myUser.getPassword())) {
				status = HttpStatus.BAD_REQUEST;
				response.setMessage("Password can't be blank");
				return new ResponseEntity<Response>(response, status);
			}
			if(ValidateData.check(myUser.getUserName())) {
				status = HttpStatus.BAD_REQUEST;
				response.setMessage("UserName can't be blank");
				return new ResponseEntity<Response>(response, status);
			}
			
			
//			check for Special characters
			if(checkspecialcharacter(myUser.getEmail())) {
				status = HttpStatus.NOT_IMPLEMENTED;
				response.setMessage("Email contain special characters");
				return new ResponseEntity<Response>(response, status);
				}
			
			if(checkspecialcharacter(myUser.getUserName())) {
				status = HttpStatus.NOT_IMPLEMENTED;
				response.setMessage("UserName contain special characters");
				return new ResponseEntity<Response>(response, status);
				}
			
			if(checkspecialcharacter(myUser.getPassword())) {
				status = HttpStatus.NOT_IMPLEMENTED;
				response.setMessage("Password contain special characters");
				return new ResponseEntity<Response>(response, status);
				}
			
			
//			format validation
			if(!emailformatvalidation(myUser.getEmail()))
			{
				status = HttpStatus.BAD_REQUEST;
				response.setMessage("Invalid email format");
				
				return new ResponseEntity<Response>(response, status);
			}
//			
			if(!panformatvalidation(myUser.getUserName()))
			{
				status = HttpStatus.BAD_REQUEST;
				response.setMessage("Invalid UserName(Pan) format");
				
				return new ResponseEntity<Response>(response, status);
			}
			
					
//			Check for duplicate data
			try {   
				User users = service.findByUserName(myUser.getUserName());
				
				if(users!=null)	{
					status = HttpStatus.CONFLICT;
					response.setMessage("User is already registered with different account");
					return new ResponseEntity<Response>(response, status);}
			}
			catch(Exception e){
				logger.debug(e.toString());
				}
		
		


			

		
			service.createUser(myUser);

			response.setMessage("User Register Successfully");
		
			
		} catch (Exception e) {
			status = HttpStatus.CONFLICT;
			response.setMessage("Invalid email format ");
			logger.debug(e.toString());
		}

		return new ResponseEntity<Response>(response, status);

	}
	
	
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	
  public ResponseEntity<Response> LoginUser(@RequestBody Login login,JwtRequest authenticationRequest){
		HttpStatus status = HttpStatus.OK;
		Response response = new Response();
try {
			
//			check for blank data			
			if(ValidateData.check(login.getUserName())) {
				status = HttpStatus.BAD_REQUEST;
				response.setMessage("UserName can't be blank");
				return new ResponseEntity<Response>(response, status);
			}
			if(ValidateData.check(login.getPassword())) {
				status = HttpStatus.BAD_REQUEST;
				response.setMessage("Password can't be blank");
				
				return new ResponseEntity<Response>(response, status);
			}
			
//			email format validation
			if(!panformatvalidation(login.getUserName()))
			{
				status = HttpStatus.BAD_REQUEST;
				response.setMessage("invalid username(pan) format");
				
				return new ResponseEntity<Response>(response, status);
			}
			
			authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

			final UserDetails userDetails = userDetailsService
					.loadUserByUsername(authenticationRequest.getUsername());

			final String token = jwtTokenUtil.generateToken(userDetails);
			ResponseEntity.ok(new JwtResponse(token));
			response.setMessage("Login Success");
			response.setToken(token);
		} catch (Exception e) {
			status = HttpStatus.UNAUTHORIZED;
			response.setMessage("Invalid Credential");
			logger.debug(e.toString());
		}

		return new ResponseEntity<Response>(response, status);
	}
		
	
	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
	
	
	@PostMapping("/create")
	User create(@RequestBody User user) {
		return service.createUser(user);
	}
	/*@GetMapping("/id/{id}")
	Optional<Login> findById(@PathVariable(value = "id") int id){
		Login user= new Login();
		User us = service.getById(id).get();
		//user.setId(us.getId());
		user.setUserName(us.getUserName());
		user.setPassword(us.getPassword());
		
		return Optional.of(user);
	}
	@PutMapping("/updateuser/{id}")
	public void updateById(@RequestBody User user,@PathVariable(value = "id") int id) {
		User update = new User(user.getFirstName(), user.getLastName(), user.getUserName(),user.getPassword(), user.getEmail(),user.getPhoneNo(), user.getDob());
		service.updateUserById(update);
	}
	@DeleteMapping("/deleteuser/{id}")
	public void deleteById(@PathVariable(value = "id") int id) {
		service.deleteUserById(id);
	}*/
}
