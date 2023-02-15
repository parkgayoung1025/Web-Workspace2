package com.kh.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.member.model.service.MemberService;
import com.kh.member.model.vo.Member;

/**
 * Servlet implementation class MemberDeleteController
 */
@WebServlet("/delete.me")
public class MemberDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberDeleteController() {
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
		
		String userPwd = request.getParameter("userPwd");
		String userId = ((Member)request.getSession().getAttribute("loginUser")).getUserId();
		
		// 로그인한 회원의 정보를 얻어오는 방법
		// 방법 1. session 영역에 담겨있는 회원 객체로부터 뽑아내기
		// 방법 2. input type="hidden" 회원 정보를 숨겨서 요청 시 함께 전달하기
		
		int result = new MemberService().deleteMember(userId, userPwd);
		// 로그아웃 처리를 할 것이기 때문에 멤버 객체에 정보를 저장할 필요도 조회할 필요도 없기 때문에 반환형이 Member가 아니고 int임
		
		HttpSession session = request.getSession();
		
		if(result > 0) { // 성공 -> 메인페이지 alert, 로그아웃 처리까지 하기
			
			// invalidate() 메서드는 사용 시 세션이 만료되어 안에 들어간 데이터가 모두 날아감 -> alert 메시지 호출할 수 없음
			// removeAttribute("loginUser") : 로그인한 사용자의 정보를 지워줌
			
			// 1. 먼저 세션 안에 담긴 데이터를 다 날린 후에 alertMsg만 따로 추가함
			// session.invalidate();
			
			session.setAttribute("alertMsg", "성공적으로 회원 탈퇴되었습니다.");
			
			// 2. 세션에서 loginUser 정보만 지워줌
			session.removeAttribute("loginUser");
			
			response.sendRedirect(request.getContextPath());
			
			// 3. 로그아웃 처리를 로그아웃 서블릿에게 위임하는 방법
			// response.sendRedirect(request.getContextPath() + "/logout.me");
			
		} else { // 실패 -> 에러페이지 포워딩 or 탈퇴 실패 문구 전송 
			session.setAttribute("alertMsg", "회원 탈퇴에 실패했습니다.");
			response.sendRedirect(request.getContextPath() + "/myPage.me");
		}
		
	}

}
