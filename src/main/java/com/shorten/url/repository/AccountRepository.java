/**
 * 
 */
package com.shorten.url.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shorten.url.domain.Account;

/**
 * @author L072426
 *
 */
@Repository
public interface AccountRepository extends JpaRepository<Account,String> {
	
}
