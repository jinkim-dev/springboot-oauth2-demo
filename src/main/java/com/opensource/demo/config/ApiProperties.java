package com.opensource.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;

@Getter
@Configuration
public class ApiProperties {

	@Value("${api.base-path}")
	private String apiBasePath;

}
