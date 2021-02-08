package com.whiteblink.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.whiteblink.model.Account;

@Repository
public interface UserRegistrationRepository extends JpaRepository<Account, Integer>{
	public Account findByFullName(String fullName);
	public Account findByUserName(String userName);
}
