package com.kh.cookie.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeleteCookieController
 */
@WebServlet("/deleteCookie.do")
public class DeleteCookieController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteCookieController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 저장된 쿠키 삭제
		String cookieId = request.getParameter("cookieId");
		
		// 1. 쿠키의 key 값을 이용하여 유효 기간을 조정하여 삭제하기
		Cookie c = new Cookie(cookieId, "");
		c.setMaxAge(0); // 동일한 이름의 key 값을 가진 cookie 객체를 생성하여 유효 시간을 0초로 만들어서 전달하게 되면 클라이언트의 쿠키가 삭제됨
		
		response.addCookie(c);
		
		response.sendRedirect(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
