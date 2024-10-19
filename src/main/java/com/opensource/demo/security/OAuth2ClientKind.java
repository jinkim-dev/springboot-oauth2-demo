package com.opensource.demo.security;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OAuth2ClientKind {
	KAKAO("kakao", KakaoUser.class),
	GOOGLE("google", GoogleUser.class);

	private final String registrationId;
	private final Class<? extends DemoUser> userClazz;

}
