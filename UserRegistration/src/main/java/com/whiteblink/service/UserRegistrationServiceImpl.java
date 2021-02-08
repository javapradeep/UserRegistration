package com.whiteblink.service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.whiteblink.exception.ErrorCode;
import com.whiteblink.exception.ExceptionLogger;
import com.whiteblink.model.Account;
import com.whiteblink.repository.UserRegistrationRepository;

@Service
public class UserRegistrationServiceImpl implements UserRegistrationService{
	@Autowired
	private UserRegistrationRepository userRegistrationRepository;


	@Override
	public String createAccount(Account account) {
		String fullName=null;
		try {
			Account account2 = userRegistrationRepository.findByUserName(account.getUserName());
			if(account2==null) {
				fullName=userRegistrationRepository.save(account).getFullName();
			} else throw new ExceptionLogger(ErrorCode.BADREQUEST.getValue(),"Account Allready Exist", HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new ExceptionLogger(ErrorCode.BADREQUEST.getValue(),e.getMessage(), HttpStatus.BAD_REQUEST);
		}

		return fullName;
	}


	@Override
	public Account getAccountByFullName(String fullName) {
		Account findByFullName=null;
		try {
			findByFullName  = userRegistrationRepository.findByFullName(fullName);
			if(findByFullName==null)
				throw new ExceptionLogger(ErrorCode.BADREQUEST.getValue(),"Name not Found!!", HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new ExceptionLogger(ErrorCode.BADREQUEST.getValue(),e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return findByFullName;
	}


	@Override
	public Account getAccountByUserName(String userName) {
		Account findByUserName=null;
		try {
			findByUserName = userRegistrationRepository.findByUserName(userName);
			if(findByUserName==null)
				throw new ExceptionLogger(ErrorCode.BADREQUEST.getValue(), "Username Not Found!!", HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			// TODO: handle exception

		}
		return  findByUserName;
	}



	@Override
	public List<Account> getAllAccounts() {
		List<Account> findAll = userRegistrationRepository.findAll();
		return findAll;
	}


	@Override
	public void deleteAccount(Integer id) {
		try {
			boolean exist = userRegistrationRepository.existsById(id);
			if(exist)
				userRegistrationRepository.deleteById(id);
			else
				throw new ExceptionLogger(ErrorCode.BADREQUEST.getValue(), "User Not Found!!",HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new ExceptionLogger(ErrorCode.BADREQUEST.getValue(),e.getMessage(),HttpStatus.BAD_REQUEST);
		}

	}



	@Override
	public void updateAccount(Account account) {
		try {
			userRegistrationRepository.save(account);
		} catch (Exception e) {
			throw new ExceptionLogger(ErrorCode.BADREQUEST.getValue(),e.getMessage(),HttpStatus.BAD_REQUEST);
		}

	}

}
