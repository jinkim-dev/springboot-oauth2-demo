package com.opensource.demo.security;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;

public class KakaoUser implements DemoUser {

	private final Map<String, Object> attributes;

	public KakaoUser(Map<String, Object> attributes) {
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
		return Optional.ofNullable(getAttributes())
			.map(attrs -> attrs.get("properties"))
			.map(properties -> {
				if (properties instanceof Map) {
					return (Map<String, Object>) properties;
				}
				return null;
			})
			.map(properties -> properties.get("nickname"))
			.map(Object::toString)
			.orElse(null);
	}
}
