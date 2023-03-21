package com.kh.member.model.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

import com.kh.common.JDBCTemplate;
import com.kh.member.model.vo.Member;

public class MemberDao {
	
	private Properties prop = new Properties();
	
	public MemberDao() {
		
		String fileName = MemberDao.class.getResource("/sql/member/member-mapper.xml").getPath();
		
		try {
			prop.loadFromXML(new FileInputStream(fileName)); // 먼저생성하고 try/catch문
		} catch (InvalidPropertiesFormatException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// DB에 접근해 실제로 존재하는 회원인지 아닌지 조회
	public Member loginMember(Connection conn, String userId, String userPwd) {
		
		// SELECT문 => 반환형 ResultSet객체(조회된 행은 1개이거나 없거나)
		// Member 객체에 담을것임
		Member m = null;
		
		// 필요한 변수들을 셋팅하는 과정이 필요함
		ResultSet rset = null;
		
		// Statement : sql문을 실행할 수 있는 객체이나 보안상 PreparedStatement로 실행
		// ResultSet 객체를 얻어오기 위해서는 PreparedStatement 객체가 sql문을 실행해줘야 결과값을 받아줄 수 있다.
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("loginMember"); // member-mapper.xml의 key값인 loginMember을 읽어들인다.
		
		try {
			pstmt = conn.prepareStatement(sql); // 생성 후 예외처리한것
			
			// sql ? 에 값들 넣어주기 -> 미완성된 쿼리문 완성됨
			pstmt.setString(1, userId);
			pstmt.setString(2, userPwd);
			
			
			// executeQuery()로 쿼리문 실행시켜서 ResultSet객체 반환
			rset = pstmt.executeQuery();
			
			// rset을 Member타입 객체로 형변환
			if(rset.next()) {
				m = new Member(rset.getInt("USER_NO"),
							   rset.getString("USER_ID"),
							   rset.getString("USER_PWD"),
							   rset.getString("USER_NAME"),
							   rset.getString("PHONE"),
							   rset.getString("EMAIL"),
							   rset.getString("ADDRESS"),
							   rset.getString("INTEREST"),
							   rset.getDate("ENROLL_DATE"),
							   rset.getDate("MODIFY_DATE"),
							   rset.getString("STATUS"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { // 사용했던 자원 반납
			try {
				rset.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return m;
	}
	
	public int insertMember(Connection conn, Member m) {
		// Insert문 => 처리된 행의 갯수
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, m.getUserId());
			pstmt.setString(2, m.getUserPwd());
			pstmt.setString(3, m.getUserName());
			pstmt.setString(4, m.getPhone());
			pstmt.setString(5, m.getEmail());
			pstmt.setString(6, m.getAddress());
			pstmt.setString(7, m.getInterest());
			
			result = pstmt.executeUpdate(); // DML문 실행
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	public int updateMember(Connection conn, Member m) {
		
		// UPDATE문 => 반환값 처리된 행의 갯수가 반환됨
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("updateMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, m.getUserName());
			pstmt.setString(2, m.getPhone());
			pstmt.setString(3, m.getEmail());
			pstmt.setString(4, m.getAddress());
			pstmt.setString(5, m.getInterest());
			pstmt.setString(6, m.getUserId());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	public Member selectMember(Connection conn, String userId) {
		
		// SELECT문 => ResultSet 객체에 저장 (id값을 unique제약조건이 걸려있어서 한 행만 조회)
		Member m = null;
		
		PreparedStatement pstmt = null;
		
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				m = new Member(rset.getInt("USER_NO"),
							   rset.getString("USER_ID"),
							   rset.getString("USER_PWD"),
							   rset.getString("USER_NAME"),
							   rset.getString("PHONE"),
							   rset.getString("EMAIL"),
							   rset.getString("ADDRESS"),
							   rset.getString("INTEREST"),
							   rset.getDate("ENROLL_DATE"),
							   rset.getDate("MODIFY_DATE"),
							   rset.getString("STATUS"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 생성된 순서의 역순으로 닫아주기
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return m;
	}
	
	public int updatePwdMember(Connection conn, String userId, String userPwd, String updatePwd) {
		
		// UPDATE => 처리된 행의 갯수 반환
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("updatePwdMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, updatePwd);
			pstmt.setString(2, userId);
			pstmt.setString(3, userPwd);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}
	
	public int deleteMember(Connection conn, String userId, String userPwd) {
		
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("deleteMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			pstmt.setString(2, userPwd);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	public String selectId(Connection conn, String checkId) {
		
		String id = null;
		
		PreparedStatement pstmt = null;
		
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectId");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, checkId);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				id = rset.getString("USER_ID");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return id;
		
		
	}
}
