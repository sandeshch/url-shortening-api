package com.shorten.url.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.shorten.url.service.UserRegistrationService;

/**
 * Pre Access filter for checking the URLs which need Authorization.
 * Authorization is done on the basis of accountId which passed as a part of header named "accountId"
 * @author sandeshch
 *
 */
@WebFilter(filterName="preAccessFilter",urlPatterns = { "/tiny/register", "/tiny/statistic" })
public class PreAccessFilter implements Filter {
	
	public static final Logger logger= LoggerFactory.getLogger(PreAccessFilter.class);
	
	@Autowired
	UserRegistrationService userRegistrationService;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		String accountId = httpRequest.getHeader("accountId");
		
		logger.info("Inside doFilter {}",accountId);
		
		if (null == accountId) {
			throw new ServletException("Valid Account Id needed to access the service");
		}

//		if (!userRegistrationService.isValidUser(accountId)) {
		if (!userRegistrationService.isDuplicateUser(accountId)) {
			throw new ServletException("User not registered, please register before you access the URL! ");
		}
		chain.doFilter(request, response);

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
