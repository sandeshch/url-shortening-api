package com.shorten.url.service.impl;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.shorten.url.domain.Url;
import com.shorten.url.dto.RegistrationRequest;
import com.shorten.url.repository.UrlRepository;
import com.shorten.url.service.UniqueKeyGenerationService;
import com.shorten.url.service.UrlShorteningService;

/**
 * @author Sandesh
 * 
 * Service IMPL for URl shortening
 *
 */
@Service
public class UrlShorteningServiceImpl implements UrlShorteningService {

	private static final String PORT = ":8080/";
	public static final Logger logger= LoggerFactory.getLogger(UrlShorteningServiceImpl.class);
	
	@Autowired
	Environment env;
	
	@Autowired
	UrlRepository urlRepository;
	
	@Autowired
	UniqueKeyGenerationService uniqueKeyGenerationService;
	
	/* (non-Javadoc)
	 * @see com.shorten.url.service.UrlShorteningService#registerUrlService(com.shorten.url.dto.RegistrationRequest, javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public String registerUrlService(RegistrationRequest request, HttpServletRequest servletRequest) throws UnknownHostException {
		
		String longUrl=request.getUrl();
		String shortUrl;
		InetAddress ip;
		//check if URL is already registered.
		Url url=urlRepository.findByLongUrl(longUrl);
		ip =InetAddress.getLocalHost();
		ip.getHostAddress();
		if(null==url){
			//generate alphanumeric string of length six digit
			shortUrl=uniqueKeyGenerationService.generateUniqueKey();
			Url finalUrl=new Url();
			finalUrl.setAccountId(servletRequest.getHeader("accountId"));
			finalUrl.setLongUrl(request.getUrl());
			finalUrl.setHits(0);
			finalUrl.setRedirectionType(request.getRedirectionType());
			finalUrl.setShortUrl(shortUrl);
			Url savedUrl=urlRepository.save(finalUrl);
		}else{
			//return the same URL if already present in DB
			shortUrl=url.getShortUrl();
		}
		
		/*return complete URL which will be used by user.
		* protocol - HTTPS/HTTP and server.port will be retrieved from application.properties file
		*/
		return shortUrl = env.getProperty("protocol") + ip.getHostAddress() + ":" + env.getProperty("server.port")+"/"+shortUrl;
		
		
	}

	/* (non-Javadoc)
	 * @see com.shorten.url.service.UrlShorteningService#getStaticService(java.lang.String)
	 */
	@Override
	public Map<String, Long> getStaticService(String accountId) {
		Map<String,Long> statMap=null;
		//Retrieve the list of URL which are registered against User
		List<Url> urls=urlRepository.findAllByAccountId(accountId);
		if(null!=urls){
			// Map the URLs and Hits as Key value pair in HashMap<String,Long>
			statMap=new HashMap<>();
			for(Url url :urls){
				logger.info("Url found {}",url.getLongUrl());
				logger.info("Hits  {}",url.getHits());
				statMap.put(url.getLongUrl(), url.getHits());
			}
			
		}
		
		return statMap;
	}
	
	/* (non-Javadoc)
	 * @see com.shorten.url.service.UrlShorteningService#retrieveLongUrl(java.lang.String)
	 */
	@Override
	public Url retrieveLongUrl(String shortUrl) {
		
		Url url=urlRepository.findByShortUrl(shortUrl);
		url.setHits(url.getHits()+1);
		url=urlRepository.save(url);
		
		return url;
		
	}
	
	

}
