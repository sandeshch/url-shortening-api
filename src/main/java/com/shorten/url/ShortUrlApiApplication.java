package com.shorten.url;

import org.h2.server.web.WebServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//@ServletComponentScan(basePackages="com.shorten.url.filter")
@ServletComponentScan
@SpringBootApplication
@EnableAutoConfiguration
@EntityScan(basePackages={"com.shorten.url.domain"})
@ComponentScan(basePackages={"com.shorten.url"})
@EnableJpaRepositories("com.shorten.url.repository")
public class ShortUrlApiApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(ShortUrlApiApplication.class, args);
	}
	
	@Bean
	public ServletRegistrationBean h2servletRegistration() {
	    ServletRegistrationBean registration = new ServletRegistrationBean(new WebServlet());
	    registration.addUrlMappings("/console/*");
	    registration.addInitParameter("webAllowOthers", "true");
	    return registration;
	}

}
