package com.shorten.url.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Url implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2554240235859974391L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;
	
	private String longUrl;
	
	private String shortUrl;
	
	private String accountId;
	
	private long hits;
	
	
	private int redirectionType;
	

	/**
	 * @return the longUrl
	 */
	public String getLongUrl() {
		return longUrl;
	}

	/**
	 * @param longUrl the longUrl to set
	 */
	public void setLongUrl(String longUrl) {
		this.longUrl = longUrl;
	}

	/**
	 * @return the shortUrl
	 */
	public String getShortUrl() {
		return shortUrl;
	}

	/**
	 * @param shortUrl the shortUrl to set
	 */
	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}

	/**
	 * @return the hits
	 */
	public long getHits() {
		return hits;
	}

	/**
	 * @param hits the hits to set
	 */
	public void setHits(long hits) {
		this.hits = hits;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getRedirectionType() {
		return redirectionType;
	}

	public void setRedirectionType(int redirectionType) {
		this.redirectionType = redirectionType;
	}

	
}
