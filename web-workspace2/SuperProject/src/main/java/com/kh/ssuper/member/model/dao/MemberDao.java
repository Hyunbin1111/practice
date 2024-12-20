package com.kh.ssuper.member.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.kh.ssuper.common.JDBCTemplate;
import com.kh.ssuper.member.model.vo.Member;

public class MemberDao {
	
	private Properties prop = new Properties();

	public MemberDao() {
		String file = MemberDao.class.getResource("/sql/member/member-mapper.xml")
									 .getPath();
		try {
			prop.loadFromXML(new FileInputStream(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public Member login(Connection conn, Member member) {
		// 로그인 READ(SELECT) => ResultSet으로 돌아옴(한 행의 정보)
		// SELECT문 => ResultSet객체(unique 제약조건에 의해 한 행만 조회됨)
		//		   => Member
		
		// 필요 변수 세팅
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		Member m = null;
		
		String sql = prop.getProperty("login");
		
		
		try {
			// pstmt 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			// binding
			pstmt.setString(1, member.getUserId());
			pstmt.setString(2, member.getUserPwd());
			
			// 쿼리문 실행, 결과받기
			rset = pstmt.executeQuery();
			
			// rset으로부터 각각의 컬럼의 값을 뽑아서 반환 Member객체 필드에 담는다
			// 조회결과가 한 행일 때		=> if(rset.next())
			// 조회결과가 여러 행일 때		=> while(rset.next())
			if(rset.next()) {
				
				// 각 컬럼의 값뽑기
				// rset.getInt / getString / getDate(뽑아올 컬럼명 또는 순번)
				m = new Member(rset.getInt("USER_NO"),
							   rset.getString("USER_ID"),
							   rset.getString("USER_PWD"),
							   rset.getString("USER_NAME"),
							   rset.getString("EMAIL"),
							   rset.getString("INTEREST"),
							   rset.getDate("ENROLL_DATE"),
							   rset.getDate("MODIFY_DATE"),
							   rset.getString("STATUS"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 자원반납
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		// Service에 결과 반환
		return m;
		
	}


	public int join(Connection conn, Member member) {

		// 변수 미리선언
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("join");
		
		try {
			// pstmt 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			// 파라미터 바인딩
			pstmt.setString(1, member.getUserId());
			pstmt.setString(2, member.getUserPwd());
			pstmt.setString(3, member.getUserName());
			pstmt.setString(4, member.getEmail());
			pstmt.setString(5, member.getInterest());
			
			// SQL문 실행 후 결과 받기
			// INSERT / UPDATE / DELETE => pstmt.executeUpdate();
			result = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		
		
		return result;
	}


	public int update(Connection conn, Member member) {
		// UPDATE => 처리된 행의 개수
		// 필요한 변수 선언
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("update");
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, member.getUserName());
			pstmt.setString(2, member.getEmail());
			pstmt.setString(3, member.getInterest());
			pstmt.setString(4, member.getUserId());
			
			result = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}


	public int updatePwd(Connection conn, int userNo, String userPwd, String updatePwd) {
		
		// UPDATE => 처리된 행의 개수
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updatePwd");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, updatePwd);
			pstmt.setInt(2, userNo);
			pstmt.setString(3, userPwd);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		
		return result;
	}


	public int delete(Connection conn, int userNo, String userPwd) {

		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("delete");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, userNo);
			pstmt.setString(2, userPwd);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		
		return result;
	}


	public int checkId(Connection conn, String id) {
		
		int count = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("checkId");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rset = pstmt.executeQuery();
			
			rset = pstmt.executeQuery();
			rset.next();
			count = rset.getInt("COUNT");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return count;
	}
	

}
