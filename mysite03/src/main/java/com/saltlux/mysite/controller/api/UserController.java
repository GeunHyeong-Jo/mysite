package com.saltlux.mysite.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.saltlux.dto.JsonResult;
import com.saltlux.mysite.service.UserService;

@Controller("userApiController")
//@RestController("userApiController")
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserService userService;

	@ResponseBody // 메시지 컨버터
	// @GetMapping("/existemail")
	@RequestMapping("/existemail")
	public JsonResult existEmail(String email) {
		Boolean result = userService.existUser(email);
		return JsonResult.success(result);
	}

	@ResponseBody
	@RequestMapping("/hello")
	public String hello() {

		return "안녕하세요";
	}

}
