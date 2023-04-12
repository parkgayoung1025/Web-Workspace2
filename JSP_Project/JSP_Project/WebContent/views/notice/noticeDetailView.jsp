<%@ page import="com.kh.notice.model.vo.Notice" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Notice n = (Notice)request.getAttribute("n");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 상세 조회 페이지</title>
</head>
<body>
	<%@ include file="../common/menubar.jsp" %>
	
	<div class="outer">
		<br>
		<h2 align="center">공지사항 상세보기</h2>
		<br>
		
		<table id="detaril.area" align="center" border="1">
			<tr>
				<th width="70">제목</th>
				<td width="350" colspan="3"><%= n.getNoticeTitle() %></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td><%= n.getNoticeWriter() %></td>
				<th>작성일</th>
				<td><%= n.getCreateDate() %></td>
			</tr>
			<tr>
				<th>내용</th>
				<td colspan="3">
					<p style="height:150px"><%= n.getNoticeContent() %></p>
				</td>
			</tr>
		</table>
		<br><br>
		
		<div align="center">
			<a href="<%= contextPath %>/list.no" class="btn btn-secondary">목록</a>
			
			<% if(loginUser != null && loginUser.getUserId().equals(n.getNoticeWriter())) { %>
				<!-- 현재 로그인을 한 사용자가 해당 글을 작성한 작성자일경우에만 보여지도록 컨트롤 -->
				<a href="<%= contextPath %>/updateForm.no?nno=<%= n.getNoticeNo() %>" class="btn btn-warning btn-sm">수정</a>
				<a href="<%= contextPath %>/delete.no?nno=<%= n.getNoticeNo() %>" class="btn btn-danger btn-sm">삭제</a>
			<% } %>
		</div>
		
	</div>
</body>
</html>