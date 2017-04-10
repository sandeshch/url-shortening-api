package com.shorten.url.serviceImpl;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.shorten.url.domain.Account;
import com.shorten.url.dto.AccountResponse;
import com.shorten.url.repository.AccountRepository;
import com.shorten.url.service.UserRegistrationService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.MOCK)
public class UserRegistrationServiceImplTest {

	@Mock
	AccountRepository accountRepository;
	
	@Autowired
	UserRegistrationService userRegistrationService;
	
	String accountId = "sandeshch";
	
	@Test
	public void testRegisterAccount() {
		Account account=new Account();
		account.setAccountId(accountId);
		account.setPassword("QW09sdrf");
		Mockito.when(accountRepository.save((Account) Mockito.any())).thenReturn(new Account());
		AccountResponse accountRes = userRegistrationService.registerAccount(accountId);
		assertTrue(accountRes.getPassword() != null);
	}

	@Test
	public void testIsDuplicateUser() {
		
		Mockito.when(accountRepository.findOne(accountId)).thenReturn(null);
		
		boolean isDuplicate  =userRegistrationService.isDuplicateUser(accountId);
		
		assertTrue(!isDuplicate);

	}

	@Test
	public void testIsValidUser() {

		Mockito.when(accountRepository.findOne(accountId)).thenReturn(new Account());
		
		boolean isValidUser  =userRegistrationService.isValidUser(accountId);
		
		assertTrue(!isValidUser);

	}

}
