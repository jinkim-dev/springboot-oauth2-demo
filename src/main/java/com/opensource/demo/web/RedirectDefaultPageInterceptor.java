package com.opensource.demo.web;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.opensource.demo.security.SecurityConfig;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class RedirectDefaultPageInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		throws Exception {
		String path  = request.getRequestURI();
		if ("/".equals(path)) {
			response.sendRedirect(SecurityConfig.HOME_API);
		}
		return true;
	}
}
