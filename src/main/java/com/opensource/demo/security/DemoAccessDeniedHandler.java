package com.opensource.demo.security;

import java.io.IOException;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DemoAccessDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
		AccessDeniedException accessDeniedException) throws IOException, ServletException {
		if (isAccessibleLoginPage(request)) {
			response.sendRedirect(SecurityConfig.HOME_API);
		} else {
			throw accessDeniedException;
		}
	}

	private boolean isAccessibleLoginPage(HttpServletRequest request) {
		return SecurityContextHolder.getContext().getAuthentication() != null &&
			request.getRequestURI().equals(SecurityConfig.LOGIN_API);
	}
}
