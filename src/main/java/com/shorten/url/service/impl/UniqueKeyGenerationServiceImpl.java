package com.shorten.url.service.impl;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shorten.url.domain.Url;
import com.shorten.url.repository.UrlRepository;
import com.shorten.url.service.UniqueKeyGenerationService;

/**
 * Class for generating Unique Keys which will be used to identify Short URL
 * @author Sandeshch
 *
 */
@Service
public class UniqueKeyGenerationServiceImpl implements UniqueKeyGenerationService{

	@Autowired
	UrlRepository urlRepository;
	
	@Override
	public String generateUniqueKey() {
		
		String shortUrl=generateShortId();
		
		while(isDuplicateShortUrl(shortUrl)){
			
			shortUrl=generateShortId();
		}
		
		return shortUrl;
		
	}

	private boolean isDuplicateShortUrl(String shortUrl){
		
		Url url=null;
			
		url=urlRepository.findByShortUrl(shortUrl);
		
		if(null!=url)
			return true;
		return false;
			
	}
	
	
private String generateShortId(){
		
		return RandomStringUtils.randomAlphanumeric(6);

	}
	

}
