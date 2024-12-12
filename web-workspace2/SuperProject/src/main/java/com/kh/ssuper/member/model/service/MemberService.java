package com.kh.ssuper.member.model.service;

import java.sql.Connection;

import com.kh.ssuper.common.JDBCTemplate;
import com.kh.ssuper.member.model.dao.MemberDao;
import com.kh.ssuper.member.model.vo.Member;

public class MemberService {
	
	static {
		JDBCTemplate.registDriver();
	}

	public Member login(Member member) {

		// 1) Service => Connection객체 생성
		Connection conn = JDBCTemplate.getConnection();
		
		// 2) DAO의 객체를 생성하면서 conn과 member를 넘겨줌
		Member m = new MemberDao().login(conn, member);
		
		// 3) Connection 객체 반납
		JDBCTemplate.close(conn);
		
		// 4) Controller에 결과 반환
		return m;
	}

	public int join(Member member) {

		Connection conn = JDBCTemplate.getConnection();
		
		int result = new MemberDao().join(conn, member);
		
		if(result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
		
	}

	public int update(Member member) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new MemberDao().update(conn, member);
		
		if(result > 0)
			JDBCTemplate.commit(conn);
		else
			JDBCTemplate.rollback(conn);
		
		JDBCTemplate.getConnection();
		
		return result;
	}

	public int updatePwd(int userNo, String userPwd, String updatePwd) {

		Connection conn = JDBCTemplate.getConnection();
		
		int result = new MemberDao().updatePwd(conn, userNo, userPwd, updatePwd);
		
		if(result > 0)
			JDBCTemplate.commit(conn);
		
		JDBCTemplate.close(conn);
		
		return result;
		
		
	}

	public int delete(int userNo, String userPwd) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new MemberDao().delete(conn, userNo, userPwd);
		
		if(result > 0)
			JDBCTemplate.commit(conn);
		
		JDBCTemplate.close(conn);
		
		return result;
	}

	public int checkId(String id) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int count = new MemberDao().checkId(conn, id);
		
		JDBCTemplate.close(conn);
		
		return count;
	}
	
	
	
	
	
	
	

}
