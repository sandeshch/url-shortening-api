package com.shorten.url.resource;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.shorten.url.domain.Url;
import com.shorten.url.service.UrlShorteningService;

/**
 * @author L072426
 *
 *REST Resource for Redirection.
 *
 */
@RestController
public class RedirectionResource {
	
	public static final Logger logger= LoggerFactory.getLogger(RedirectionResource.class);
	
	@Autowired
	UrlShorteningService urlShorteningService;

	/**
	 * Redirection method which takes short URL as parameter, retrieves corresponding Long URL and redirects. 
	 * 
	 * @param shortUrl
	 * @param response
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	@GetMapping(value = "/{shortUrl}", produces = MediaType.APPLICATION_XHTML_XML_VALUE)
	public void redirectUrl(@PathVariable String shortUrl,
			HttpServletResponse response) throws URISyntaxException,
			IOException {
		
		

		Url url = urlShorteningService.retrieveLongUrl(shortUrl);
		logger.info("Redirection Type from DB{}",url.getRedirectionType());
		//Setting status as saved in DB at the time of URL registration.
		response.setStatus(url.getRedirectionType());
		response.setHeader("Location", url.getLongUrl());
	}

}
