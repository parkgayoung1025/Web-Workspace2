package com.kh.notice.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		int userNo = Integer.parseInt(request.getParameter("userNo"));
		
		int result = new NoticeService().insertNotice(title, content, userNo);
	
		if(result > 0) {
			request.getSession().setAttribute("alertMsg", "성공적으로 공지사항이 등록되었습니다.");
			// 성공시 list.no로 이동
			response.sendRedirect(request.getContextPath()+"/list.no");	
			
			// 글 등록 성공시 내가 작성한 게시글로 곧바로 이동
			
		}else {
			request.getSession().setAttribute("alertMsg", "공지사항 등록 실패.");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
		}
	}

}













