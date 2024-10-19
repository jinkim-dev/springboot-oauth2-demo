package com.opensource.demo.config;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;

@Getter
@Configuration
public class OAuthProperties {

	@Value("${oauth.client.kakao.client-id}")
	private String kakaoClientId;

	@Value("${oauth.client.kakao.token-uri}")
	private URI kakaoTokenUri;

	@Value("${oauth.client.kakao.logout-uri}")
	private URI kakaoLogoutUri;

	@Value("${oauth.client.kakao.logout-redirect-uri}")
	private URI kakaoLogoutRedirectUri;
}
