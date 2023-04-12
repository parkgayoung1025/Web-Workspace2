package com.kh.ajaxController;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

/**
 * Servlet implementation class JqAjaxController2
 */
@WebServlet("/jqAjax2.do")
public class JqAjaxController2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JqAjaxController2() {
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
		
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		
		// ajax라는 녀석은 결과를 딱 한 개만 응답할 수 있음.
		// 요청 처리를 다 했다는 가정하에 응답할 데이터를 "문자열" 형태로 셋팅
		
		// 버전 1) 
//		String responseData = "이름 : "+name+", 나이 : "+age;
//		
//		response.setContentType("text/html; charset=UTF-8");
//		response.getWriter().print(responseData);
	
		// 버전2) 응답데이터 여러 개 보내고 싶을 때 "JSON"형태로 데이터 셋팅
		/*
		 * JSON(JavaScript Object Notaion : 자바스크립트의 객체 표기법)
		 *  - ajax 통신 시 데이터 전송에 사용되는 포맷 중에 하나
		 *  
		 *  - JSON 처리 시 사용되는 클래스 종류
		 *   => 기본적으로 자바에서 제공하지 않는다(라이브러리 필요)
		 *   json-simple-x.x.x.jar 다운로드 후 WEB-INF/lib폴더에 복사해 줘야 함.
		 *  
		 *   https://code.google.com/archive/p/json-simple/downloads
		 * 	 
		 *   1. JSONArray[value, value, value] : 배열 형태
		 *   2. JSONObject{key:value, key:value} : 객체 형태	
		 * 
		 */
//		 JSONArray jArr = new JSONArray();// []
//		 jArr.add(name);// ["홍길동"]
//		 jArr.add(age);// ["40"]
//		 
//		 //응답할 데이터의 contentType을 설정. text/html -> 문자열
//		 response.setContentType("apllication/json; charset=UTF-8");
//		 
//		 response.getWriter().print(jArr);
		
		// JSONObject로 객체 전달 해 주기
		JSONObject jobj = new JSONObject(); // {}
		jobj.put("name", name);// {name : "가영"}
		jobj.put("age", age);// {name : "가영", age : 20}
		 
		response.setContentType("apllication/json; charset=UTF-8");
		
		response.getWriter().print(jobj);
	}

}
