package com.kh.notice.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.notice.model.service.NoticeService;
import com.kh.notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeInsertController
 */
@WebServlet("/insert.no")
public class NoticeInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("UTF-8");
		
		
		String noticeTitle = request.getParameter("title");
		String noticeContent = request.getParameter("content");
		String noticeWriter = request.getParameter("userNo");
		
//		String title = request.getParameter("title");
//		String content = request.getParameter("content");
//		String userNo = request.getParameter("userNo");
		
//		Notice n = new Notice(noticeTitle, noticeContent, noticeWriter);
//		위 코드를 사용하려면 아래처럼 Notice에 String 자료형 세개 추가해 줘야 함.
//		public Notice(String noticeTitle, String noticeContent, String noticeWriter) {
//			super();
//			this.noticeTitle = noticeTitle;
//			this.noticeContent = noticeContent;
//			this.noticeWriter = noticeWriter;
//		}
		
		Notice n = new Notice();
		n.setNoticeTitle(noticeTitle);
		n.setNoticeContent(noticeContent);
		n.setNoticeWriter(noticeWriter);
		
//		Notice n = new Notice();
//		n.setNoticeTitle(title);
//		n.setNoticeContent(content);
//		n.setNoticeWriter(userNo);
		
		int result = new NoticeService().insertNotice(n);
		
		if(result >0 ) {
			request.getSession().setAttribute("alertMsg", "글이 작성되었습니다.");
//			response.sendRedirect(request.getContextPath()+"/list.no");
			
			// 글 등록에 성공했을 때 내가 작성한 게시글로 곧 바로 이동
			response.sendRedirect(request.getContextPath()+"/detail.no?nno="+result);
		} else {
			request.setAttribute("errorMsg", "공지사항 등록 실패");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
		}
	}

}
