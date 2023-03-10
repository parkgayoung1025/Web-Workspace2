<%@page import="com.kh.model.vo.Person"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>JSTL Functions Library</h1>
	
	<c:set var="str" value="      How are you?"/>
	
	str : ${str} <br>
	문자열의 길이 : ${fn:length(str)} 글자입니다. <br>
	문자열의 길이 : ${str.length()} 글자입니다. <br><br>
	
	<%
		ArrayList<Person> list = new ArrayList<>();
		list.add(new Person("박가영", "여", "학생"));
		list.add(new Person("가영", "여", "개발자"));
		list.add(new Person("박나영", "여", "백수"));
		
		request.setAttribute("list", list);
	%>
	
	배열의 길이 : ${list.size()} <br><br>
	
	문자열을 대문자로 출력 : ${fn:toUpperCase(str)} <br>
	문자열을 모두 소문자로 출력 : ${fn:toLowerCase(str)} <br><br>
	
	are의 시작 인덱스 : ${fn:indexOf(str, 'are')} <br>
	are의 시작 인덱스 : ${fn:indexOf(str, 'are') > -1} <br>
	문자열을 변환 : ${fn:replace(str, "are", "were")} <br>
	
	원본 str : ${str} <br><br>
	
	str에 "are"이라는 문자열이 포함되어 있나? : ${fn:contains(str, "are") ? '포함되어 있습니다.' : '없습니다.'} <br><br>
	<!-- indexOf, contains : 조건식이나 삼항연산식에 사용 가능 -->
	
	문자열 앞, 뒤 공백 제거 : ${fn:trim(str)} <br>
	문자열 잘라주기 : ${fn:substring(fn:trim(str), 0, 3)} <br>
	문자열 잘라주기2 : ${fn:substringAfter(str, "are")} <br>
	문자열 나누기 : ${fn:split(str, ' ')} <br>
	<c:forEach var="s" items="${fn:split(str, ' ')}">
		${s} <br>
	</c:forEach>
	배열 합치기 : ${fn:join(fn:split(str, ' '), ',')} <br>
	
	<c:set var="htmlStr" value="<p>hi</p>"/>
	${htmlStr}
	태그를 문자열로 바꿔주는 함수 : ${fn:escapeXml(htmlStr)}	
</body>
</html>