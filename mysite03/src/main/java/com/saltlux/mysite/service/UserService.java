package com.saltlux.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saltlux.mysite.repository.UserRepository;
import com.saltlux.mysite.vo.UserVo;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public void join(UserVo vo) {
		userRepository.insert(vo);
		// 메일 보내기
	}

	public UserVo getUser(UserVo vo) {
		return userRepository.findByEmailAndPassword(vo);
	}

	public UserVo getUser(Long no) {
		 return userRepository.findByNo(no);
	}

}
