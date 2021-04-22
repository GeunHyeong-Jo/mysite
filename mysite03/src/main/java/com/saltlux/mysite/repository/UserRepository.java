package com.saltlux.mysite.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.saltlux.mysite.exception.UserRepositoryException;
import com.saltlux.mysite.vo.UserVo;

@Repository
public class UserRepository {

	@Autowired
	private SqlSession sqlSession;

	public UserVo findByEmailAndPassword(UserVo vo) throws UserRepositoryException {
		return sqlSession.selectOne("findByEmailAndPassword", vo);
	}

	public boolean insert(UserVo vo) {
		System.out.println(vo.getNo());
		int count = sqlSession.insert("user.insert", vo);
		System.out.println(vo.getNo());
		return count == 1;
	}

	public UserVo findByNo(Long no) {
		return sqlSession.selectOne("user.find", no);
	}

	public boolean update(UserVo vo) {
		int count = sqlSession.update("user.update", vo);
		return count == 1;
	}
}
