package com.kh.ssuper.member.model.dao;

import org.apache.ibatis.session.SqlSession;

import com.kh.ssuper.member.model.vo.Member;

public class MemberDao {

	public int insertMember(SqlSession sqlSession, Member member) {
		/* 원래 방식
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertMember");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getUserId());
			pstmt.setString(2, member.getUserPwd());
			....
		} catch (~~) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result
		*/
		/*
		 *	sqlSession.insert("SQL문", "SQL문을 실행할 때 필요한 데이터")
		 *	
		 * sqlSession.sql문종류에 맞는 메소드("매퍼파일의namespace속성값") 
		 */
		// 위치홀더에 값 담는걸 해야함, 이제 DAO에서 진행하지않고 mapper에서 진행한다.
		
		//int result = sqlSession.insert("memberMapper.insertMember", member);
		//return result;
		
		return sqlSession.insert("memberMapper.insertMember", member);
	}

	public Member login(SqlSession sqlSession, Member member) {
		// .selectOne 을 쓸건지, .selectList 를 쓸건지 정하기.
		// 받아온 결과값이 하나일 경우 .selectOne
		// 받아온 결과값을 list에 담아야할 경우 .selectList
		// sqlSession.selectOne("mapper의 namespace속성값.select태그의 id속성값");
		//Member loginMember =sqlSession.selectOne("memberMapper.login", member);
		//return loginMember;
		// selectOne() : 조회결과가 존재하지 않는다면 null을 반환
		return sqlSession.selectOne("memberMapper.login", member);
	}

}
