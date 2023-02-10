package com.kh.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCTemplate {
	
	// 1. Connection 객체 생성 한 후 해당 Connection 반환하는 메서드
	public static Connection getConnection() {
		
		Properties prop = new Properties(); // Map 계열 컬렉션(key-value)
		
		// 읽어들이고자 하는 driver.properties 파일의 물리적인 경로
		
		String fileName = JDBCTemplate.class.getResource("/sql/driver/driver.properties").getPath();
		// JDBCTemplate.class는 컴파일 된 class 파일을 의미
		// getResource 함수의 첫 번째 /는 classes 폴더를 의미함
		
		//String fileName = "C:\web-workspace2\JSP_Project\WebContent\WEB-INF\classes\sql\driver";
		
		try {
			prop.load(new FileInputStream(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Connection conn = null;
		
		// 1) jdbc 드라이버 등록
		try {
			Class.forName(prop.getProperty("driver"));
			
			// 2) DB와 접속 후 Connection 객체 생성
			conn = DriverManager.getConnection(prop.getProperty("url"), 
					   prop.getProperty("username"),
					   prop.getProperty("password"));
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	// 2. 전달받은 Connection 객체를 가지고 commit 해 주는 메서드
	
	// 3. 전달받은 Connection 객체를 가지고 rollback 해 주는 메서드
	
	// 4. Connection 객체를 반납해 주는 메서드
	
	// 5. Statement 객체를 반납시켜주는 메서드
	
	// 6. ResultSet 객체를 반납시켜주는 메서드 
}
