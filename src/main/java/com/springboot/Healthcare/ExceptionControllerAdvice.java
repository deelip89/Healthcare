package com.springboot.Healthcare;

import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionControllerAdvice {
	@org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
	public String ExceptionHandler(Exception e) {
		return e.getMessage();
	}

}
