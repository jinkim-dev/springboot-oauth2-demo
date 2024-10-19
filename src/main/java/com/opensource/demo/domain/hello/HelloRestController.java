package com.opensource.demo.domain.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloRestController {

	@GetMapping("/api/v1beat1/hello")
	public String hello() throws Exception {
		return "hello";
	}
}
