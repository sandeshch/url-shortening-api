package com.shorten.url.dto;

import java.io.Serializable;

public class AccountRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7153138403247974206L;

	private String accountId;

	/**
	 * @return the accountId
	 */
	public String getAccountId() {
		return accountId;
	}

	/**
	 * @param accountId the accountId to set
	 */
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	
	
}
