package com.kh.cookie.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CheckCookieController
 */
@WebServlet("/checkCookie.do")
public class CheckCookieController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckCookieController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// client가 보낸 cookie 값들 확인
		// cookie 값은 request 객체 안에 담겨있고, getcookies() 메서드를 이용하여 꺼내올 수 있다.
		// session 값도 request 객체 안에 담겨있고, getSession() 메서드를 이용하여 꺼내왔음
		// getCookise() 함수의 반환형은 Cookie[]임 (서버에 저장되는 쿠키 값이 한 개가 아니기 때문에)
		
		Cookie[] cookies = request.getCookies(); // 저장된 쿠키가 없다면 -> null 값이 반환됨
		
		if (cookies != null) {
			for (Cookie c :  cookies) {
				// Cookie 값을 확인하는 메서드 : getName(), getValue()
				System.out.println("키 값 : " + c.getName() + ", value 값 : " + c.getValue());
			}
		}
		
		request.getRequestDispatcher("views/responsePage.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
