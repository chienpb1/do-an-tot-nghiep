package com.chienpb.service.IMPL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.chienpb.model.Account;
import com.chienpb.model.CustomAccountDeails;
import com.chienpb.service.AccountService;

public class CustomAccountDetailsService implements UserDetailsService{
	
	@Autowired
	private AccountService aService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Account account = aService.findByUsername(username);
		if(account == null) {
			throw new UsernameNotFoundException("user not found");
		}
		return new CustomAccountDeails(account);
	}
	
}
