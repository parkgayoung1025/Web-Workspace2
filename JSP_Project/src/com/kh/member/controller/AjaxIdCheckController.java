package com.kh.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.member.model.service.MemberService;

/**
 * Servlet implementation class AjaxIdCheckController
 */
@WebServlet("/idCheck.me")
public class AjaxIdCheckController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxIdCheckController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 1. 사용자가 전달한 데이터 변수화
		String checkId = request.getParameter("checkId");
		
		// 2. db에 현재 전달된 데이터가 존재하는지 확인
		// db에있는 아이디의 값을 select해 checkId와 비교
		// db에 있는 아이디를 얻어올 변수 설정
		String id = new MemberService().selectId(checkId);
//		System.out.println(id);
//		System.out.println(checkId);
		
		response.setContentType("text/html; charset=UTF-8");
		
		// 3. 중복된 아이디가 존재하는 케이스 , 존재하지 않는 케이스 별로 데이터 전달
		if(id != null) { // 존재하는 케이스
			response.getWriter().print(id);
		}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
