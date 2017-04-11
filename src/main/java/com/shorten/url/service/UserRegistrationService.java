package com.shorten.url.service;

import com.shorten.url.dto.AccountResponse;

public interface UserRegistrationService {

	/**
	 * Register the user with accountId is being provided by the User, 
	 * it will check if already register and will provide password to the user
	 * @param accountId
	 * @return
	 */
	public AccountResponse registerAccount(String accountId);
	
	/**
	 * Method for checking if the user already registered.
	 * @param accountId
	 * @return
	 */
	public boolean  isDuplicateUser(String accountId);
	
	/**
	 * Method for checking if User is valid user.
	 * @param accountId
	 * @return
	 */
	public boolean isValidUser(String accountId);
	
}
