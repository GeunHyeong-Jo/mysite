package com.saltlux.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saltlux.mysite.repository.BoardRepository;
import com.saltlux.mysite.vo.BoardVo;
import com.saltlux.mysite.vo.PagerVo;

@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;

	public int getCount() {
		return boardRepository.getCount();
	}
	
	public List<BoardVo> findAll(PagerVo vo) {
		return boardRepository.findAll(vo);
	}



}
