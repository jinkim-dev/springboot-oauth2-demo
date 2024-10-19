package com.opensource.demo.security;

import java.io.IOException;
import java.util.Optional;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import com.opensource.demo.exception.DemoExceptionHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DemoAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint {

	private String apiBasePath;

	public DemoAuthenticationEntryPoint(String loginFormUrl, String apiBasePath) {
		super(loginFormUrl);
		this.apiBasePath = apiBasePath;
	}

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
		AuthenticationException authException) throws IOException, ServletException {
		if (isRestApi(request)) {
			DemoExceptionHandler.handleException(response, authException);
		} else {
			super.commence(request, response, authException);
		}
	}

	private boolean isRestApi(HttpServletRequest request) {
		return Optional.ofNullable(request.getRequestURI())
			.map(uri -> uri.contains(apiBasePath))
			.orElse(false);
	}

}
