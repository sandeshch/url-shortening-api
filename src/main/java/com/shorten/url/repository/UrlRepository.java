package com.shorten.url.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shorten.url.domain.Url;
@Repository
public interface UrlRepository extends JpaRepository<Url, String>{

	public abstract Url findByLongUrl(String longUrl);

	public abstract Url findByShortUrl(String shortUrl);
	
	public abstract List<Url> findAllByAccountId(String accountId);
}
