package com.opensource.demo.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

public interface DemoUser extends UserDetails, OAuth2User {
	String getUsername();
}
