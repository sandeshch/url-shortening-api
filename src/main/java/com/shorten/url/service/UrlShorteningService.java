package com.shorten.url.service;

import java.net.UnknownHostException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.shorten.url.domain.Url;
import com.shorten.url.dto.RegistrationRequest;

public interface UrlShorteningService {

	public String registerUrlService(RegistrationRequest request, HttpServletRequest servletRequest) throws UnknownHostException;
	
	public Map<String,Long> getStaticService(String accountId);
	
	public Url retrieveLongUrl(String shortUrl);
	
	
}
