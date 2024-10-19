package com.opensource.demo.util;

import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.opensource.demo.security.DemoUser;

public abstract class SecurityContextUtil {
	public static String getUsername() {
		return Optional.ofNullable(getDemoUser())
			.map(DemoUser::getUsername)
			.orElse(null);
	}

	public static DemoUser getDemoUser() {
		return Optional.ofNullable(SecurityContextHolder.getContext())
			.map(SecurityContext::getAuthentication)
			.map(Authentication::getPrincipal)
			.map(p -> (DemoUser) p)
			.orElse(null);
	}
}
