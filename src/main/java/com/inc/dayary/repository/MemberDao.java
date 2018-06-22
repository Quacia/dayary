package com.inc.dayary.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.inc.dayary.domain.Member;

@Repository
public class MemberDao {

	@Autowired
	private SqlSession sqlSession;

	public Member findOne(String id) {
		return sqlSession.selectOne("member.findOne", id);
	}

	public Member getFindOneByEmail(String email) {
		return sqlSession.selectOne("member.findOneByEmail", email);
	}

	public void signup(Member member) {
		sqlSession.insert("member.signup", member);
	}
	
}
