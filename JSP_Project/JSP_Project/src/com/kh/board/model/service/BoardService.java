package com.kh.board.model.service;

import static com.kh.common.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.kh.board.model.dao.BoardDao;
import com.kh.board.model.vo.Attachment;
import com.kh.board.model.vo.Board;
import com.kh.board.model.vo.Category;
import com.kh.board.model.vo.Reply;
import com.kh.common.model.vo.PageInfo;

public class BoardService {

	public int selectListCount() {
		
		Connection conn = getConnection();
		
		int listCount = new BoardDao().selectListCount(conn);
		
		close(conn);
		
		return listCount;
		
	}
	
	// selectList(PageInfo pi) : 사용자가 요청한 페이지의 게시글 목록을 불러오는 함수
	public ArrayList<Board> selectList(PageInfo pi){
	
		Connection conn = getConnection();
		
		ArrayList<Board> list = new BoardDao().selectList(conn, pi);
		
		close(conn);
		
		return list;
	}
	
	public ArrayList<Category> selectCategoryList(){
		
		Connection conn = getConnection();
		
		ArrayList<Category> list = new BoardDao().selectCategoryList(conn);
		
		close(conn);
		
		return list;
	}
	
	public int insertBoard(Board b, Attachment at) {
		
		Connection conn = getConnection();
		
		int result1 = new BoardDao().insertBoard(conn, b);
		
		// Attachment 테이블에 등록여부를 판단할 변수
		int result2 = 1; // 1로 미리 선언과 동시에 초기화시켜주는 이유는, Attachment테이블에 Insert문이 실행되지 않을수도 있으므로
		
		if(at != null) {
			result2 = new BoardDao().insertAttachment(conn, at);
		}
		
		// 트랜잭션 처리
		if(result1 > 0 && result2 > 0) {
			// 첨부파일이 없는 경우 insert가 성공했을 때도 result2는 여전히 0이기 때문에 rollback처리가 될 수 있음
			// 따라서 애초에 result2의 값을 1로 초기회 시켜줘야한다
			commit(conn);
		}else {
			rollback(conn);
		}

		close(conn);
		
		return result1 * result2; // 혹시 하나라도 실패해서 0이 반환될 경우 아예 실패값을 반환하기위해 곱셈결과를 리턴
		
	}
	
	public int increaseCount(int bno) {
		
		Connection conn = getConnection();
		
		int result = new BoardDao().increaseCount(conn, bno);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		
		return result;
		
	}
	
	public Board selectBoard(int bno) {
		
		Connection conn = getConnection();
		
		Board b = new BoardDao().selectBoard(conn, bno);
		
		close(conn);
		
		return b;
	}
	
	public Attachment selectAtachment(int bno) {
		
		Connection conn = getConnection();
		
		Attachment at = new BoardDao().selectAtachment(conn, bno);
		
		close(conn);
		
		return at;
	}
	
	public int updateBoard(Board b, Attachment at) {
		
		Connection conn = getConnection();
		
		int result1 = new BoardDao().updateBoard(conn, b);
		
		int result2 = 1; // 애초에 insert나 update문이 실행조차 되지 않았을경우를 대비해서 1로 초기회시킴
		
		// 새롭게 첨부된 첨부파일이 있는경우 -> update, insert문을 실행
		if(at != null) {
			// 기존첨부파일이 있는 경우 -> update문 실행하기 위해 fileNo값이 필요
			if(at.getFileNo() != 0) {
				result2 = new BoardDao().updateAttachment(conn, at);
			}else { // 기존첨부파일이 없는 경우 -> insert문
				result2 = new BoardDao().insertUpdateAttachment(conn, at);
			}
		}
		
		if(result1 > 0 && result2 > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		
		return result1 * result2;
	}
	
	public int deleteBoard(int bno, int userNo, Attachment at) {
		
		Connection conn = getConnection();
		
		int result = new BoardDao().deleteBoard(conn, bno, userNo);
		int result2 = 1;
		
		if(at != null) {
			result2 = new BoardDao().deleteAttachment(conn, bno);
		}
		
		if(result > 0 && result2 > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return result * result2;
	}
	
	public int insertThumbnailBoard(Board b, ArrayList<Attachment> list) {
		
		Connection conn = getConnection();
		
		int result1 = new BoardDao().insertThumbnailBoard(conn, b);
		
		int result2 = new BoardDao().insertAttachmentList(conn, list);
		
		if(result1 > 0 && result2 > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		
		return result1 * result2;
	}
	
	public ArrayList<Board> selectThumbnailList(){
		
		Connection conn = getConnection();
		
		ArrayList<Board> list = new BoardDao().selectThumbnailList(conn);
		
		close(conn);
		
		return list;
	}
	
	public Board selectThumbnailBoard(int bno) {
		
		Connection conn = getConnection();
		
		Board b = new BoardDao().selectThumbnailBoard(conn, bno);
		
		close(conn);
		
		return b;
	}
	
	public ArrayList<Attachment> selectThumbnailAttachment(int bno) {
		
		Connection conn = getConnection();
		
		ArrayList<Attachment> list = new BoardDao().selectThumbnailAttachment(conn, bno);
		
		close(conn);
		
		return list;
	}
	
	public int insertReply(Reply r) {
		Connection conn = getConnection();
		
//		int result = new BoardDao().insertReply(conn, bno, content, replyWriter);
		int result = new BoardDao().insertReply(conn, r);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		return result;
	}
	
	public ArrayList<Reply> selectReply(int bno){
		
		Connection conn = getConnection();
		
		ArrayList<Reply> list = new BoardDao().selectReply(conn, bno);
		
		close(conn);
		
		return list;
	}
	
}






















