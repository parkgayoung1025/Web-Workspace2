package com.kh.notice.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.notice.model.service.NoticeService;
import com.kh.notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeUpdateController
 */
@WebServlet("/update.no")
public class NoticeUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeUpdateController() {
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
		
//		Notice updateN = new NoticeService().updateNotice(noticeTitle, noticeContent, noticeWriter);
		
//		if(updateN == null) {
//			request.getSession().setAttribute("alertMsg", "글이 수정되었습니다.");
////			response.sendRedirect(request.getContextPath()+"/list.no");
//			
//			// 글 등록에 성공했을 때 내가 작성한 게시글로 곧 바로 이동
//			response.sendRedirect(request.getContextPath()+"/detail.no?nno="+result);
//		} else {
//			request.setAttribute("errorMsg", "공지사항 수정 실패");
//			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
//		}
	}

}
