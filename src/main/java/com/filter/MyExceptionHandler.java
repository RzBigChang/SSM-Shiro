package com.filter;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice
public class MyExceptionHandler {

	@ExceptionHandler
	@ResponseBody
	public String ErrorHandler(AuthorizationException authorizationException) {
		return "没有权限";
	}
}
