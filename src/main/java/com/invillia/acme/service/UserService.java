package com.invillia.acme.service;

import org.springframework.security.core.context.SecurityContextHolder;

import com.invillia.acme.security.UserSS;

public class UserService {

	public static UserSS authenticated() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch(Exception ex) {
			return null;
		}
	}
}
