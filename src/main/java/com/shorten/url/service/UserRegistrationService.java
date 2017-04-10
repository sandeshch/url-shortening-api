package com.shorten.url.service;

import com.shorten.url.dto.AccountResponse;

public interface UserRegistrationService {

	public AccountResponse registerAccount(String accountId);
	
	public boolean  isDuplicateUser(String accountId);
	
	public boolean isValidUser(String accountId);
	
}
