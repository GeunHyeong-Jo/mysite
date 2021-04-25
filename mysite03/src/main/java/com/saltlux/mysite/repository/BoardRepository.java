package com.saltlux.mysite.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.saltlux.mysite.vo.BoardVo;
import com.saltlux.mysite.vo.PagerVo;

@Repository
public class BoardRepository {// https://drsggg.tistory.com/222

	@Autowired
	private SqlSession sqlSession;

	public int getCount() { // 모든 글의 갯수
		int count = sqlSession.selectOne("board.selectcount");
		return count;
	}

	public boolean updateOrder(Long g_no, Long o_no) {// 원글의 g_no, o_no를 입력받는다
		HashMap<String, Long> map = new HashMap<String, Long>();
		map.put("g_no", g_no);
		map.put("o_no", o_no);
		int count = sqlSession.update("board.updateorder", map);
		return count == 1;
	}

	public List<BoardVo> findAll(PagerVo vo) { // 전체 리스트 출력을 위해서
		List<BoardVo> list = new ArrayList<>();
		list = sqlSession.selectList("board.findall", vo);
		return list;
	}

	public Long getMaxGno() { // 제일 높은 g_no를 얻어온다
		Long g_no = sqlSession.selectOne("board.maxg_no");
		return g_no;
	}

	public BoardVo findbyno(Long boardNo) {
		BoardVo vo = sqlSession.selectOne("board.findbyno", boardNo);
		return vo;
	}

	public void insert(BoardVo vo) {
		sqlSession.insert("board.insert", vo);
	}

	public void updateView(Long no) {
		sqlSession.update("board.updateview", no);
	}

	public void update(BoardVo vo) {
		sqlSession.update("board.update", vo);
	}

	public void delete(BoardVo vo) {
		sqlSession.delete("board.delete", vo);
	}

}
