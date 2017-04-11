package com.shorten.url.resource;

import java.net.UnknownHostException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import javax.ws.rs.core.Context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shorten.url.domain.Url;
import com.shorten.url.dto.AccountRequest;
import com.shorten.url.dto.AccountResponse;
import com.shorten.url.dto.RegistrationRequest;
import com.shorten.url.dto.RegistrationResponse;
import com.shorten.url.service.UrlShorteningService;
import com.shorten.url.service.UserRegistrationService;

/**
 * Resource for APIs of Users account and URL registration . 
 * @author Sandesh
 *
 */
@RestController
@RequestMapping(value = "/tiny")
public class ShorteningResource {

	@Autowired
	UserRegistrationService userRegistrationService;

	@Autowired
	UrlShorteningService urlShorteningService;

	/**
	 * API for registering new User
	 * @param request
	 * @return response with status and password
	 */
	@PostMapping(value = "/account", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public AccountResponse registerAccount(@NotNull @RequestBody AccountRequest request) {

		AccountResponse response = null;
		String accountId=request.getAccountId();
		response = userRegistrationService.registerAccount(accountId);

		return response;
	}

	/**
	 * Method for registering new URL.
	 * @param request
	 * @param servletRequest
	 * @return
	 * @throws UnknownHostException if InetAdderss is not able to resolve the IP address
	 */
	@PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public RegistrationResponse registerUrl(
			@NotNull @RequestBody RegistrationRequest request,
			@Context HttpServletRequest servletRequest) throws UnknownHostException {
		RegistrationResponse response=new RegistrationResponse();
		String shortUrl = urlShorteningService.registerUrlService(request, servletRequest);
		response.setShortUrl(shortUrl);
		return response;
	}

	/**
	 * API for getting statics of the All the URLs which are registered against user
	 * @param accountId
	 * @return
	 */
	@GetMapping(value = "/statistic/{accountId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String,Long> getStatistics(
			@PathVariable("accountId") String accountId) {

		Map<String,Long> stats=urlShorteningService.getStaticService(accountId);
		return stats;
	}

}
