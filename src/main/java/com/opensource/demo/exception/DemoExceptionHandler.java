package com.opensource.demo.exception;

import java.io.IOException;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class DemoExceptionHandler {

	private static ObjectMapper objectMapper;

	public DemoExceptionHandler(ObjectMapper objectMapper) {
		DemoExceptionHandler.objectMapper = objectMapper;
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handle(HttpServletRequest request, Exception e) {
		return ResponseEntity.badRequest().body(e.getMessage());
	}

	public static void handleException(HttpServletResponse response, Exception e) {
		try {
			Map<String, String> body = Map.of("message", e.getMessage());
			response.getWriter().write(objectMapper.writeValueAsString(body));
		} catch (IOException ioExcept) {
			ioExcept.printStackTrace();
		}
	}

}
