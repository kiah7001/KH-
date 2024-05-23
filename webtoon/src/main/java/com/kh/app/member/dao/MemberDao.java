package com.kh.app.member.dao;

import org.apache.ibatis.session.SqlSession;

import com.kh.app.member.vo.MemberVo;

public class MemberDao {

	public int join(SqlSession ss, MemberVo vo) {
		return ss.insert("MemberMapper.join" , vo);
		
	}
	
}
