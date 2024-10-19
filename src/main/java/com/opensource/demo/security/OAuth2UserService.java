package com.opensource.demo.security;

import java.lang.reflect.Constructor;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OAuth2UserService extends DefaultOAuth2UserService {

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		OAuth2User oAuth2User = super.loadUser(userRequest);
		String registrationId = userRequest.getClientRegistration().getRegistrationId();
		return convert(registrationId, oAuth2User);
	}

	public DemoUser convert(String registrationId, OAuth2User oAuth2User) {
		DemoUser demoUser = null;
		OAuth2ClientKind kind = OAuth2ClientKind.valueOf(registrationId.toUpperCase());
		Constructor<?>[] c= kind.getUserClazz().getConstructors();
		try {
			demoUser = ((DemoUser) c[0].newInstance(oAuth2User.getAttributes()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return demoUser;
	}
}
