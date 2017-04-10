package com.shorten.url.dto;

public class RegistrationRequest {
	
	String url;
	int redirectionType;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getRedirectionType() {
		return redirectionType;
	}
	public void setRedirectionType(int redirectionType) {
		this.redirectionType = redirectionType;
	}

}
