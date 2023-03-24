package com.kh.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.sql.Connection;

public class JDBCTemplate {
	
	// 1. Connection객체 생성 한 후 해당 Connection반환하는 메소드
	// Connection : DB와의 연결 정보를 담고 있는 객체(IP주소, PORT 번호, 계정명, 비밀번호)
	public static Connection getConnection() {
		
		// DB와 연결하고자하는 연결정보 -> Properties에 따로 뺄것임
		Properties prop = new Properties(); // Map 계열 컬렉션(key-value)
		
		// 읽어들이고자하는 driver.properties파일의 물리적인 경로
		String fileName = JDBCTemplate.class.getResource("/sql/driver/driver.properties").getPath();
		// JDBCTemplate.class는 컴파일된 class파일을 의미
		// getResource함수의 첫번째 /는 classes폴더를 의미함
		
		// String fileName = "C:\Web-Workspace2\JSP_Project\WebContent\WEB-INF\classes\sql\driver";
		
		try {
			prop.load(new FileInputStream(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Connection conn = null;
		
		// 1) JDBC 드라이버 등록
		try {
			Class.forName(prop.getProperty("driver"));
			
			// 2) DB와 접속 후 Connection객체 생성
			conn = DriverManager.getConnection(prop.getProperty("url"), 
											   prop.getProperty("username"),
											   prop.getProperty("password"));
			// 3) 자동커밋 설정 해제
			conn.setAutoCommit(false);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	// 2. 전달받은 Connection 객체를 가지고 commit 해주는 메소드
	public static void commit(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) {
				conn.commit();				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 3. 전달받은 Connection 객체를 가지고 rollback 해주는 메소드
	public static void rollback(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) {
				conn.rollback();				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 4. Connection 객체를 반납해주는 메소드
	public static void close(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) {
				conn.close();				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 5. Statement 객체를 반납시켜주는 메소드
	// close 오버로딩
	public static void close(Statement stmt) {
		try {
			if(stmt != null && !stmt.isClosed()) {
				stmt.close();				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 6. ResultSet 객체를 반납시켜주는 메소드
	public static void close(ResultSet rset) {
		try {
			if(rset != null && !rset.isClosed()) {
				rset.close();				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
