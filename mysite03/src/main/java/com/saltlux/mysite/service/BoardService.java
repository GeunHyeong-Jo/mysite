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

	public Long getMaxGno(){
		return boardRepository.getMaxGno();
	}

	public BoardVo findByNo(Long boardNo) {
		return boardRepository.findbyno(boardNo);
	}

	public void updateOrder(Long g_no, Long o_no) {
		boardRepository.updateOrder(g_no, o_no);
	}

	public void insert(BoardVo vo) {
		boardRepository.insert(vo);
	}

	public void updateView(Long no) {
		boardRepository.updateView(no);
	}

	public void update(BoardVo vo) {
		boardRepository.update(vo);
	}

	public void delete(BoardVo vo) {
		boardRepository.delete(vo);
	}


}
