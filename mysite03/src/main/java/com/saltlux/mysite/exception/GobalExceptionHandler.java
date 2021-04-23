package com.saltlux.mysite.exception;

import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.saltlux.dto.JsonResult;

@ControllerAdvice
public class GobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	public void handleException(HttpServletRequest request, HttpServletResponse response, Exception e)
			throws Exception {
		// 1. 로깅

		System.out.println(e);

		// 2. 요청 구분 (html인지 json인지)
		String accept = request.getHeader("accept");
		if (accept.matches(".*application/json.*")) { // 정규표현식 이용
			/* JSON 응답 */
			response.setStatus(HttpServletResponse.SC_OK);
			
			JsonResult jsonResult =JsonResult.fail(e.toString());
			String jsonString =new ObjectMapper().writeValueAsString(jsonResult);
			
			OutputStream os = response.getOutputStream();
			os.write(jsonString.getBytes("utf-8"));
			os.close();
			
			
			
		} else {
			// 2. 사과
			request.setAttribute("error", e.toString());
			request.getRequestDispatcher("/WEB-INF/views/error/exception.jsp").forward(request, response);
			;
		}
	}

}
