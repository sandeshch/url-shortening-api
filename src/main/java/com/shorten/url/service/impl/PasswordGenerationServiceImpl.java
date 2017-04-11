package com.shorten.url.service.impl;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Service;

import com.shorten.url.service.PasswordGenerationService;

/**
 * Utility class for Password generation
 * @author Sandeshch
 *
 */
@Service
public class PasswordGenerationServiceImpl implements PasswordGenerationService {

	public String generatePassword(){
		
		return RandomStringUtils.randomAlphanumeric(8);

	}
}
