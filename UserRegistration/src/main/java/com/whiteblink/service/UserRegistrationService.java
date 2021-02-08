package com.whiteblink.service;

import java.util.List;

import com.whiteblink.model.Account;

public interface UserRegistrationService {

	public String createAccount(Account account); 
	public Account getAccountByFullName(String fullName);
	public Account getAccountByUserName(String userName);
	public List<Account> getAllAccounts();
	public void deleteAccount(Integer id);
	public void updateAccount(Account account);

}
