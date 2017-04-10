package com.shorten.url.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.shorten.url.domain.Account;
import com.shorten.url.dto.AccountResponse;
import com.shorten.url.repository.AccountRepository;
import com.shorten.url.repository.UrlRepository;
import com.shorten.url.service.PasswordGenerationService;
import com.shorten.url.service.UserRegistrationService;

@Service
public class UserRegistrationServiceImpl implements UserRegistrationService {

	public static final Logger logger= LoggerFactory.getLogger(UserRegistrationServiceImpl.class);
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	UrlRepository urlRepository;
	
	@Autowired
	PasswordGenerationService passwordGenerationService;
	
	@Override
	public AccountResponse registerAccount(String accountId) {
		
		logger.info("registerAccount {}", accountId);
		
		String pwd;
		Account account=new Account();
		account.setAccountId(accountId);
		AccountResponse response=new AccountResponse();
		pwd=passwordGenerationService.generatePassword();
		account.setPassword(pwd);
		
		if(isDuplicateUser(accountId)){
			response.setSuccess(false);
			response.setDescription("Account already exist with same username");
			return response;
		}
		logger.info("Inside UserRegistrationService registerAccount account {}",accountId);
		Account acctResponse=accountRepository.save(account);
		logger.info("account retrieved : Account {}",acctResponse.toString());
		response.setDescription("Your account opened successfully");
		response.setSuccess(true);
		response.setPassword(acctResponse.getPassword());
		
		return response;
	}

	@Override
	public boolean isDuplicateUser(String accountId) {
		logger.info("Inside UserRegistrationService isDuplicateUser account {}",accountId);
		Account duplicateAccount=accountRepository.findOne(accountId);
		
		if(null!=duplicateAccount)
		{
			logger.info("account retrieved : Account {}",duplicateAccount.toString());	
		return true;
		}
	return false;
		
	}

	@Override
	public boolean isValidUser(String accountId) {

		logger.info("Inside UserRegistrationServiceImpl:isValidUser account {}",accountId);
		
		Account retrievedAccount=accountRepository.findOne(accountId);
		
		logger.info("account retrieved : account {}",retrievedAccount);
		
		if(null==retrievedAccount){
			
			return false;
		}
		
		if(null==retrievedAccount.getAccountId()){
			logger.info("account retrieved : account {}",retrievedAccount.toString());
			return false;
		}
		return true;
	}
	
	

}
