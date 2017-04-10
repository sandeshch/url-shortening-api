package com.shorten.url.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class StatisticResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3250989678390346265L;

	private Map<String,Long> urls;

	public Map<String, Long> getUrls() {
		return urls;
	}

	public void setUrls(Map<String, Long> urls) {
		this.urls = urls;
	}
	
	
	
}
