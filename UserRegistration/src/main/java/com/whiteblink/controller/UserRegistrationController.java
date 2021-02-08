package com.whiteblink.controller;

import java.util.List;
import java.util.Optional;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.whiteblink.exception.ErrorCode;
import com.whiteblink.exception.ExceptionLogger;
import com.whiteblink.model.Account;
import com.whiteblink.repository.UserRegistrationRepository;
import com.whiteblink.service.UserRegistrationService;

import javassist.NotFoundException;

@RestController
@RequestMapping("/app")
public class UserRegistrationController {
	@Autowired
	private UserRegistrationService userRegistrationService;
	@Autowired
	private UserRegistrationRepository userRegistrationRepository;

	//Create Account

	@PostMapping("/createAccount")
	public ResponseEntity<String> createAccount(@RequestBody Account account){
		String  createAccount=null;
		try {
			createAccount = userRegistrationService.createAccount(account);
		} catch (Exception e) {
			throw new ExceptionLogger(ErrorCode.BADREQUEST.getValue(),e.getMessage(), HttpStatus.BAD_REQUEST);
		}

		return ResponseEntity.ok(createAccount+" save Successfully");
	}

	//Get FullName

	@GetMapping("/getAccountByFullName/{fullName}")	
	public ResponseEntity<Account> getAccountByFullName(@PathVariable("fullName")String fullName){
		Account accountByFullName=null;
		try {
			accountByFullName = userRegistrationService.getAccountByFullName(fullName);
		} catch (Exception e) {
			throw new ExceptionLogger(ErrorCode.BADREQUEST.getValue(),e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok(accountByFullName);
	}

	// Get UserName

	@GetMapping("/getAccountByUserName/{userName}")
	public ResponseEntity<Account> getAccountByUserName(@PathVariable("userName")String userName){
		Account accountByUserName=null;
		try {
			accountByUserName = userRegistrationService.getAccountByUserName(userName);
		} catch (Exception e) {
			throw new ExceptionLogger(ErrorCode.BADREQUEST.getValue(), e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok(accountByUserName);
	}

	// Get AllAccount

	@GetMapping("/getAllAccounts")
	public ResponseEntity<List<Account>> getAllAccounts(){
		List<Account> allAccounts = userRegistrationService.getAllAccounts();
		return ResponseEntity.ok(allAccounts);
	}

	// Delete Account

	@DeleteMapping("/deteteAccountById/{id}")
	public ResponseEntity<String> deletebyId(@PathVariable("id") Integer id){
		try {
			userRegistrationService.deleteAccount(id);
		} catch (Exception e) {
			throw new ExceptionLogger(ErrorCode.BADREQUEST.getValue(),e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok("Account deleted Successfully");	
	}


	// Update Account

	@PutMapping("/updateAccount/{id}")
	public ResponseEntity<String> updateAccount(@RequestBody Account account ,@PathVariable("id")Integer id){
		try {
			Optional<Account> optional = userRegistrationRepository.findById(id);
			if(optional.isPresent())
				userRegistrationService.updateAccount(account);
			else
				throw new ExceptionLogger(ErrorCode.BADREQUEST.getValue(), "User Not Found!!",HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new ExceptionLogger(ErrorCode.BADREQUEST.getValue(),e.getMessage(),HttpStatus.BAD_REQUEST);
		}

		return ResponseEntity.ok(id+" Account updated");
	}
}
