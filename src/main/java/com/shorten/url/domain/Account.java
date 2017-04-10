package com.shorten.url.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.stereotype.Repository;

@Entity
public class Account {
	@Id
	private String accountId;
	
	private String password;

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Account [accountId=").append(accountId).append(", password=").append(password).append("]");
		return builder.toString();
	}
	
	
}
