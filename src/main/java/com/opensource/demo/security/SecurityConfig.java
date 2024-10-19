package com.opensource.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.security.web.SecurityFilterChain;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opensource.demo.config.ApiProperties;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

	public static final String LOGIN_API = "/login";
	public static final String HOME_API = "/home";
	private final OAuth2UserService oAuth2UserService;
	private final ApiProperties apiProperties;

	@Bean
	ObjectMapper objectMapper() {
		return new ObjectMapper();
	}

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http
			.csrf(AbstractHttpConfigurer::disable)
			.headers(customizer -> customizer.frameOptions(FrameOptionsConfig::sameOrigin))
			.authorizeHttpRequests(authorize -> authorize.requestMatchers("/images/**").permitAll().requestMatchers(LOGIN_API).anonymous().anyRequest().authenticated())
			.oauth2Login(
				customizer -> customizer.loginPage(LOGIN_API).userInfoEndpoint(config -> config.userService(oAuth2UserService))
					.redirectionEndpoint(c -> c.baseUri("/auth/code/**/callback"))
			)
			.logout(c -> c.logoutSuccessUrl("/"))
			.exceptionHandling(customizer -> customizer.authenticationEntryPoint(new DemoAuthenticationEntryPoint(LOGIN_API,
				apiProperties.getApiBasePath())).accessDeniedHandler(new DemoAccessDeniedHandler()))
			.build();
	}

}
