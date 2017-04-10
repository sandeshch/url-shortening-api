package com.shorten.url.dto;

import java.io.Serializable;

public class AccountResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1561393214590247576L;
	
	private boolean isSuccess;
	
	private String description;
	
	private String password;

	/**
	 * @return the isSuccess
	 */
	public boolean isSuccess() {
		return isSuccess;
	}

	/**
	 * @param isSuccess the isSuccess to set
	 */
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	} 
	
	
	
}
