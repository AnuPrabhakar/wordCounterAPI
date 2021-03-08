package com.wordcount.demo.security;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SpringBootSecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		  auth.inMemoryAuthentication()
		  .withUser("user").password("{noop}password").roles("USER");
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {	
		 http.csrf().disable().authorizeRequests().anyRequest().authenticated()
		.and().httpBasic();
	}
}
