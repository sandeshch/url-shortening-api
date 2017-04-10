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
	
	@Override
	public String registerUrlService(RegistrationRequest request, HttpServletRequest servletRequest) throws UnknownHostException {
		
		String longUrl=request.getUrl();
		String shortUrl;
		InetAddress ip;
		Url url=urlRepository.findByLongUrl(longUrl);
		ip =InetAddress.getLocalHost();
		ip.getHostAddress();
		if(null==url){
			shortUrl=uniqueKeyGenerationService.generateUniqueKey();
			Url finalUrl=new Url();
			finalUrl.setAccountId(servletRequest.getHeader("accountId"));
			finalUrl.setLongUrl(request.getUrl());
			finalUrl.setHits(0);
			finalUrl.setRedirectionType(request.getRedirectionType());
			finalUrl.setShortUrl(shortUrl);
			Url savedUrl=urlRepository.save(finalUrl);
		}else{
			shortUrl=url.getShortUrl();
		}
			return shortUrl = env.getProperty("protocol") + ip.getHostAddress() + ":" + env.getProperty("server.port")+"/"+shortUrl;
		
		
	}

	@Override
	public Map<String, Long> getStaticService(String accountId) {
		Map<String,Long> statMap=null;
		
		List<Url> urls=urlRepository.findAllByAccountId(accountId);
		if(null!=urls){
			statMap=new HashMap<>();
			for(Url url :urls){
				logger.info("Url found {}",url.getLongUrl());
				logger.info("Hits  {}",url.getHits());
				statMap.put(url.getLongUrl(), url.getHits());
			}
			
		}
		
		return statMap;
	}
	
	public Url checkDuplicateRegistration(String urlBase){
		
		Url url=urlRepository.findByLongUrl(urlBase);
		
		if(null!=url)
			return url;
		return null;
		
		
	}

	@Override
	public Url retrieveLongUrl(String shortUrl) {
		
		Url url=urlRepository.findByShortUrl(shortUrl);
		url.setHits(url.getHits()+1);
		url=urlRepository.save(url);
		
		return url;
		
	}
	
	

}
