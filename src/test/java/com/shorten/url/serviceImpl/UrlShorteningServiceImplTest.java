/**
 * 
 */
package com.shorten.url.serviceImpl;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.shorten.url.domain.Url;
import com.shorten.url.dto.RegistrationRequest;
import com.shorten.url.repository.UrlRepository;
import com.shorten.url.service.UrlShorteningService;

/**
 * @author Sandesh
 * 
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class UrlShorteningServiceImplTest {

	private static final String SHORT_URL = "dm3H8K";

	public static final String STACKOVERFLOW_LONG_URL = "http://stackoverflow.com/questions/11721622/how-do-i-pass-the-httpservletrequest-object-to-the-test-case";

	@Mock
	UrlRepository urlRepository;

	@Autowired
	@InjectMocks
	UrlShorteningService urlShorteningService;

	/**
	 * Test method for
	 * {@link com.shorten.url.service.impl.UrlShorteningServiceImpl#registerUrlService(com.shorten.url.dto.RegistrationRequest, javax.servlet.http.HttpServletRequest)}
	 * .
	 * 
	 * @throws UnknownHostException
	 */
	@Test
	public void testRegisterUrlService() throws UnknownHostException {

		HttpServletRequest mockRequest = Mockito.mock(HttpServletRequest.class);
		Mockito.when(mockRequest.getParameter("accountId")).thenReturn("sandeshch");

		Url url = new Url();
		populateUrl(url);
		Mockito.when(urlRepository.save((Url) Mockito.any())).thenReturn(url);
		RegistrationRequest registrationRequest = new RegistrationRequest();
		registrationRequest.setRedirectionType(301);
		registrationRequest.setUrl(STACKOVERFLOW_LONG_URL);
		String shortUrl = urlShorteningService.registerUrlService(registrationRequest, mockRequest);

		Assert.assertTrue(null != shortUrl);

	}

	/**
	 * @param url
	 */
	private void populateUrl(Url url) {
		url.setAccountId("sandeshch");
		url.setId(SHORT_URL);
		url.setLongUrl(STACKOVERFLOW_LONG_URL);
		url.setRedirectionType(302);
	}

	/**
	 * Test method for
	 * {@link com.shorten.url.service.impl.UrlShorteningServiceImpl#getStaticService(java.lang.String)}
	 * .
	 */
	@Test
	public void testGetStaticService() {
		String accountId = "sandeshch";
		Url url = new Url();
		url.setHits(3);
		populateUrl(url);
		List<Url> urlList = new ArrayList<>();
		urlList.add(url);
		Mockito.when(urlRepository.findAllByAccountId((String) Mockito.any())).thenReturn(urlList);

		Map<String, Long> statMap = urlShorteningService.getStaticService(accountId);

		Assert.assertEquals(new Long(3), statMap.get(STACKOVERFLOW_LONG_URL));
	}

	/**
	 * Test method for
	 * {@link com.shorten.url.service.impl.UrlShorteningServiceImpl#retrieveLongUrl(java.lang.String)}
	 * .
	 */

	@Test
	public void testRetrieveLongUrl() {
		
		Url  url=new Url();
		
		populateUrl(url);
		
		Mockito.when(urlRepository.findByShortUrl((String) Mockito.any())).thenReturn(url);
		
		Mockito.when(urlRepository.save((Url) Mockito.any())).thenReturn(url);

			Url result=urlShorteningService.retrieveLongUrl(SHORT_URL);
			
			Assert.assertEquals(STACKOVERFLOW_LONG_URL, result.getLongUrl());

	}

}
