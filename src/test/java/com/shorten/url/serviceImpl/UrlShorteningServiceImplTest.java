/**
 * 
 */
package com.shorten.url.serviceImpl;

import static org.junit.Assert.fail;

import java.net.UnknownHostException;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;

import com.shorten.url.domain.Url;
import com.shorten.url.dto.RegistrationRequest;
import com.shorten.url.repository.UrlRepository;
import com.shorten.url.service.UniqueKeyGenerationService;
import com.shorten.url.service.UrlShorteningService;

/**
 * @author Sandesh
 * 
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class UrlShorteningServiceImplTest {

	@Mock
	UrlRepository urlRepository;

	@Autowired
	UniqueKeyGenerationService uniqueKeyGenerationService;

	@Autowired
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

		HttpServletRequest mockRequest =Mockito.mock(HttpServletRequest.class);
		Mockito.when(mockRequest.getParameter("accountId")).thenReturn("sandeshch");
		
		Url url = new Url();
		populateUrl(url);

		Mockito.when(urlRepository.save((Url) Mockito.any())).thenReturn(url);
		

		RegistrationRequest registrationRequest = new RegistrationRequest();
		registrationRequest.setRedirectionType(301);
		registrationRequest
				.setUrl("http://stackoverflow.com/questions/11721622/how-do-i-pass-the-httpservletrequest-object-to-the-test-case");
		String shortUrl = urlShorteningService.registerUrlService(
				registrationRequest, mockRequest);

		Assert.assertTrue(null != shortUrl);

	}

	/**
	 * @param url
	 */
	private void populateUrl(Url url) {
		url.setAccountId("sandeshch");
		url.setId("dm3H8K");
		url.setLongUrl("http://stackoverflow.com/questions/11721622/how-do-i-pass-the-httpservletrequest-object-to-the-test-case");
		url.setRedirectionType(302);
	}

	/**
	 * Test method for
	 * {@link com.shorten.url.service.impl.UrlShorteningServiceImpl#getStaticService(java.lang.String)}
	 * .
	 */
	/*@Test
	public void testGetStaticService() {
		fail("Not yet implemented");
	}*/

	/**
	 * Test method for
	 * {@link com.shorten.url.service.impl.UrlShorteningServiceImpl#checkDuplicateRegistration(java.lang.String)}
	 * .
	 */
	/*@Test
	public void testCheckDuplicateRegistration() {
		fail("Not yet implemented");
	}*/

	/**
	 * Test method for
	 * {@link com.shorten.url.service.impl.UrlShorteningServiceImpl#retrieveLongUrl(java.lang.String)}
	 * .
	 */
	/*@Test
	public void testRetrieveLongUrl() {
		fail("Not yet implemented");
	}*/

}
