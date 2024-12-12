package com.kh.ssuper.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCTemplate {
	
	public static void registDriver() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		// Properties 내부의 데이터 값이 변할때마다 값이 적용된다.
		
		Connection conn = null;
		Properties prop = new Properties();
		
		try {
			// driver.properties를 찾아갈 수 있는 경로를 지정
			String filePath = JDBCTemplate.class
										  .getResource("/sql/driver/driver.properties")
										  .getPath();
			
			prop.load(new FileInputStream(filePath));
			
			
			
			conn = DriverManager.getConnection(
											   prop.getProperty("url")
											 , prop.getProperty("username")
											 , prop.getProperty("password"));
					// 셋다 문자열
					// 셋다 db에 접근하기위한 정보

			conn.setAutoCommit(false);
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
		return conn;		
	}
	
	
	public static void commit(Connection conn) {	
		try {
			if(conn != null && !conn.isClosed()) conn.commit();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void rollback(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) conn.rollback();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void close(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) conn.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}	
	}
	
	
	
	public static void close(Statement stmt) {
		try {
			if(stmt != null && !stmt.isClosed()) stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void close(ResultSet rset) {
		try {
			if(rset != null && !rset.isClosed()) rset.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
}
