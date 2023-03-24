<%@ page import="com.kh.common.model.vo.PageInfo, java.util.ArrayList, com.kh.board.model.vo.Board" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	PageInfo pi = (PageInfo)request.getAttribute("pi");
	ArrayList<Board> list = (ArrayList<Board>)request.getAttribute("list");
	
	int currentPage = pi.getCurrentPage();
	int startPage = pi.getStartPage();
	int endPage = pi.getEndPage();
	int maxPage = pi.getMaxPage();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>일반 게시판</title>
<style>
	.list-area{
		border: 1px solid white;
		text-align: center;
	}
	.list-area>tbody>tr:hover{
		background: gray;
		cursor: pointer;
	}
</style>
</head>
<body>
	<%@ include file="../common/menubar.jsp" %>
	
	<div class="outer">
		<br>
		<h2 align="center">일반게시판</h2>
		<br>
		
		<!-- 글 등록 버튼(로그인한 회원만 보이도록) -->
		<% if(loginUser != null){ %>
			<div align="right" style="width: 850px">
				<a href="<%= contextPath %>/insert.bo" class="btn btn-secondary">글작성</a>
				<br><br>
			</div>
		<%} %>
		
		<table class="list-area" align="center">
			<thead>
				<tr>
					<th width="70">글번호</th>
					<th width="70">카테고리</th>
					<th width="300">제목</th>
					<th width="100">작성자</th>
					<th width="50">조회수</th>
					<th width="100">작성일</th>
				</tr>
			</thead>
			<tbody>
				<% if(list.isEmpty()){ %>
					<tr>
						<td colspan="6">조회된 리스트가 없습니다.</td>
					</tr>
				<%} else{ %>
					<% for(Board b : list){ %>
						<tr>
							<td><%= b.getBoardNo() %></td>
							<td><%= b.getCategory() %></td>
							<td><%= b.getBoardTitle() %></td>
							<td><%= b.getBoardWriter() %></td>
							<td><%= b.getCount() %></td>
							<td><%= b.getCreateDate() %></td>
						</tr>
					<%} %>
				<%} %>
			</tbody>
		</table>
		
		<br><br>
		
		<!-- 페이징바 영역 -->
		
		<div align="center" class="paging-area">
		
			<!-- 시작페이지가 첫화면이 아닐 경우 + 페이지 이동값 -->
			<% if(currentPage != 1){ %>
				<button onclick="location.href = '<%= contextPath %>/list.bo?currentPage=<%= currentPage -1 %>'">&lt;</button>
			<%} %>
			
			<% for(int i = startPage; i <= endPage; i++){ %>
				
				<!-- 현재 있는 페이지는 클릭할 수 없도록 볼수 있게 설정 -->
				<% if(i != currentPage){ %>
					<button onclick="location.href = '<%= contextPath %>/list.bo?currentPage=<%= i %>';"><%= i %></button>
				<%} else{ %>
					<button disabled><%= i %></button>
				<%} %>
			<%} %>
			
			<!-- 현재 요청한 페이지가 마지막페이지가 아닐 경우 + 페이지 이동값 -->
			<% if(currentPage != maxPage){ %>
				<button onclick="location.href = '<%= contextPath %>/list.bo?currentPage=<%= currentPage + 1 %>'">&gt;</button>
			<%} %>
			
		</div>
	</div>
	
	<script>
		$(function() {
			$(".list-area>tbody>tr").click(function() {
				
				let bno = $(this).children().eq(0).text();
				
				location.href = "<%= contextPath %>/detail.bo?bno="+bno;
				
			})
		});
	</script>
</body>
</html>