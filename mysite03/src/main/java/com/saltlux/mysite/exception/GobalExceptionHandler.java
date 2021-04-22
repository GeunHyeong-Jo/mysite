package com.saltlux.mysite.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	public void handleException(
			HttpServletRequest request,
			HttpServletResponse response,
			Exception e) throws Exception {
		// 1. 로깅
		
		System.out.println(e);

		// 2. 사과
		request.setAttribute("error", e.toString());
		request.getRequestDispatcher("/WEB-INF/views/error/exception.jsp").forward(request, response); ;

	}

}
