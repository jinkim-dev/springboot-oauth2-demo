package com.opensource.demo.security;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;

public class GoogleUser implements DemoUser {

	private final Map<String, Object> attributes;

	public GoogleUser(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	@Override
	public String getName() {
		return null;
	}

	@Override
	public Map<String, Object> getAttributes() {
		return this.attributes;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return null;
	}

	@Override
	public String getUsername() {
		return getAttrValue("name");
	}

	@Override
	public String getEmailAddress() {
		return getAttrValue("email");
	}

	private String getAttrValue(String key) {
		return Optional.ofNullable(getAttributes())
			.map(attrs -> attrs.get(key))
			.map(Object::toString)
			.orElse(null);
	}

}
