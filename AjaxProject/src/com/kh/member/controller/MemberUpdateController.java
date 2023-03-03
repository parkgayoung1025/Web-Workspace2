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
 * Servlet implementation class MemberUpdateController
 */
@WebServlet("/update.me")
public class MemberUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberUpdateController() {
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
		// 1) 인코딩 설정
		request.setCharacterEncoding("UTF-8");
		
		// 2) 요청 시 전달값들을 뽑아서 변수 및 객체에 담기
		String userId = request.getParameter("userId"); // 필수 값
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
		
		Member m = new Member(userId, userName, phone, email, address, interest);
		
		// 3) 회원가입 요청 처리
		Member updateMem = new MemberService().updateMember(m);
		
		// 4) 돌려받은 처리 결과를 가지고 사용자가 보게 될 응답 뷰를 지정
		if(updateMem == null) { // 정보 변경 실패
			request.setAttribute("errorMsg", "회원정보 수정에 실패했습니다.");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
		} else { // 성공
			
			// 변경된 회원정보를 session 영역에 다시 한번 저장
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", updateMem); // 같은 키값은 존재할 수 없음.(덮어씌우기 가능)
			session.setAttribute("alertMsg", "성공적으로 회원정보를 수정했습니다.");
			
			// session.invalidate();
			response.sendRedirect(request.getContextPath()+"/myPage.me");
		}
	}

}
