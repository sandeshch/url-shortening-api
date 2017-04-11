package com.shorten.url.service;

import java.net.UnknownHostException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.shorten.url.domain.Url;
import com.shorten.url.dto.RegistrationRequest;

public interface UrlShorteningService {

	/**
	 * This method saves the URL which is provided by the user for Registration. All the registration will be agains account id.
	 * @param request
	 * @param servletRequest
	 * @return
	 * @throws UnknownHostException
	 */
	public String registerUrlService(RegistrationRequest request, HttpServletRequest servletRequest) throws UnknownHostException;
	
	/**
	 * Statistic method retrieves all the URLs which are registered against User 
	 * 
	 * @param accountId
	 * @return URL - Hits map
	 */
	public Map<String,Long> getStaticService(String accountId);
	
	/**
	 * Method retrieves the Long URL based on Short URL provided.
	 * 
	 * @param shortUrl
	 * @return
	 */
	public Url retrieveLongUrl(String shortUrl);
	
	
}
