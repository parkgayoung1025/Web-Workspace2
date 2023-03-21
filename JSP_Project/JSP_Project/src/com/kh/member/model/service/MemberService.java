package com.kh.member.model.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.kh.common.JDBCTemplate;
import com.kh.member.model.dao.MemberDao;
import com.kh.member.model.vo.Member;

// DB에 접근해서 얻어온 변수들을가지고 일치항목을 찾기위해
// Controller에서 정보를 넘겨 처리하게끔 한 곳
public class MemberService {

	public Member loginMember(String userId, String userPwd) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		// Connection객체와 userId, userPwd를 가지고 MemberDao()를 호출해서 
		// DB에 접근해 실제로 존재하는 아이디, pwd인지 체크해보도록 함
		Member m = new MemberDao().loginMember(conn, userId, userPwd);
		
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return m;
	}
	
	public int insertMember(Member m) {
		// 반환형 int : 처리된 행의 갯수
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new MemberDao().insertMember(conn, m);
		
		// 트랜잭션 처리
		if(result > 0) { // 성공
			JDBCTemplate.commit(conn); // 커밋
		}else { // 실패
			JDBCTemplate.rollback(conn); // 롤백
		}
		
		// 사용한 자원 반납 conn.close();
		JDBCTemplate.close(conn);
		
		// 컨트롤러에게 결과값 반환(처리된 행의 갯수)
		return result;
	}
	
	/**
	 * 회원 정보 수정용 서비스
	 * @param m : 수정할 회원의 정보를 담은 Member객체
	 * @return => 수정한 회원의 갱신된 정보
	 */
	public Member updateMember(Member m) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new MemberDao().updateMember(conn, m);
		
		Member updateMem = null;
		
		if(result > 0) { // 성공
			JDBCTemplate.commit(conn);
			
			updateMem = new MemberDao().selectMember(conn, m.getUserId());
		}else { // 실패
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return updateMem;
	}
	
	public Member updatePwdMember(String userId, String userPwd, String updatePwd) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new MemberDao().updatePwdMember(conn, userId, userPwd, updatePwd);
		
		Member updateMem = null;
		
		if(result > 0) {
			JDBCTemplate.commit(conn);
			
			updateMem = new MemberDao().selectMember(conn, userId);
		}else {
			JDBCTemplate.rollback(conn);
			
		}
		JDBCTemplate.close(conn);
		
		return updateMem;
		
	}
	
	public int deleteMember(String userId, String userPwd) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new MemberDao().deleteMember(conn, userId, userPwd);
		
		if(result > 0) {
			JDBCTemplate.commit(conn);
			
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		
		return result;
	}
	
	public String selectId(String checkId) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		String id = new MemberDao().selectId(conn, checkId);
		
		JDBCTemplate.close(conn);
		
		return id;
	}
}

