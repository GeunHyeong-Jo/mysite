package com.saltlux.mysite.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.saltlux.mysite.service.UserService;
import com.saltlux.mysite.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join() {
		return "user/join";
	}

	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(UserVo vo) {
		userService.join(vo);
		return "redirect:/user/joinsuccess";
	}

	@RequestMapping(value = "/joinsuccess")
	public String joinsuccess() {
		return "user/joinsuccess";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {

		return "user/login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(UserVo vo, HttpSession session) {
		UserVo authUser = userService.getUser(vo);
		if(authUser==null) {
			return "redirect:/user/login?result=fail";
		}
		
		
		/* 로그인 처리 */
		session.setAttribute("authUser", authUser);
		return "redirect:/";
	}
}
