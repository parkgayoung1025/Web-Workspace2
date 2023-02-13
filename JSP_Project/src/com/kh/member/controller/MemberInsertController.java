package com.kh.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.member.model.service.MemberService;
import com.kh.member.model.vo.Member;

/**
 * Servlet implementation class MemberInsertController
 */
@WebServlet("/insert.me")
public class MemberInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberInsertController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	// 1) 인코딩 작업
		request.setCharacterEncoding("UTF-8");
		
	// 2) 요청 시 전달값을 뽑아서 변수, 객체에 기록해 줄 것
		String userId = request.getParameter("userId"); // 필수 값
		String userPwd = request.getParameter("userPwd"); // 필수 값
		String userName = request.getParameter("userName"); // 필수 값
		String phone = request.getParameter("phone"); // 빈 문자열이 전달 될 수도 있다.
		String email = request.getParameter("email"); // 빈 문자열이 전달 될 수도 있다.
		String address = request.getParameter("address"); // 빈 문자열이 전달 될 수도 있다.
		String[] interestArr = request.getParameterValues("interest"); // null 값이 전달된 수 있음
		
		// DB에 저장하기 위해 String[] --> String으로 변환
		// ["운동", "등산"] --> "운동, 등산"
		
		String interest = "";
		if(interestArr != null) {
			interest = String.join(",", interestArr);
		}
		
		// 전달받은 파라미터를 가지고 Member 클래스로 만들어 주기
		Member m = new Member(userId, userPwd, userName, phone, email, address, interest);
		
		// 3) 요청 처리(서비스 메서드 호출하고 결과값 돌려받기)
		int result = new MemberService().insertMember(m);
		
		// 4) 처리 결과를 가지고 사용자가 보게 될 응답 화면을 지정
		if(result > 0) { // 성공 => /jspproject(url 재요청 방식)
			request.getSession().setAttribute("alertMsg", "회원가입에 성공했습니다.");
			
			response.sendRedirect(request.getContextPath());
		} else { // 실패 => 에러 페이지 포워딩
			request.setAttribute("errorMsg", "회원가입에 실패했습니다.");
			
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
		}
	}

}
