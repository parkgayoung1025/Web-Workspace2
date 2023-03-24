<%@ page import="java.util.ArrayList, com.kh.board.model.vo.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Board b = (Board)request.getAttribute("b");
	ArrayList<Attachment> list = (ArrayList<Attachment>)request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사진게시판 상세 조회 페이지</title>
<style>
	
</style>
</head>
<body>
	<%@ include file="../common/menubar.jsp" %>
	
	<div class="outer">
		<br> <h2 style="text-align: center;">사진게시판 상세보기</h2> <br>
		
		<table class="detail-area" align="center">
			<tr>
				<td width="70">제목</td>
				<td colspan="3" width="600"><%= b.getBoardTitle() %></td>
			</tr>
			<tr>
				<td>작성자</td>
				<td><%= b.getBoardWriter() %></td>
				<td>작성일</td>
				<td><%= b.getCreateDate() %></td>
			</tr>
			<tr>
				<td>내용</td>
				<td colspan="3">
					<p style="height: 50px"><%= b.getBoardContent() %></p>
				</td>
			</tr>

<%-- 			<% for(Attachment at :list) { %> --%>
<%-- 				<% if( at.getFileLevel() == 1){ %> --%>
<!-- 					<tr> -->
<!-- 						<td>대표사진</td> -->
<!-- 						<td colspan="3"> -->
<%-- 							<img src="<%= contextPath %><%= at.getFilePath() + at.getChangeName() %>" width="500" height="300"> --%>
<!-- 						</td> -->
<!-- 					</tr> -->
<%-- 				<% } %> --%>
<%-- 			<% } %> --%>
			<!-- 위 아래 같은 코드(list에서 뽑아오는 방식이 다름) -->
			<tr>
				<td>대표사진</td>
				<td colspan="3">
					<img src="<%= contextPath + list.get(0).getFilePath() + list.get(0).getChangeName() %>" width="500" height="300">
				</td>
			</tr>
			
			<tr>
				<td>상세사진</td>
				<td colspan="3">
					<% for(Attachment at :list) { %>
						<% if( at.getFileLevel() != 1){ %>
							<img src="<%= contextPath %><%= at.getFilePath() + at.getChangeName() %>" width="200" height="150">
						<% } %>
					<% } %>
				</td>
			</tr>
				
		</table>
		
		<br>
		
		<div align="center">
			<a href="<%= contextPath %>/list.th" class="btn btn-secondary btn-sm">목록가기</a>
			<a href="<%= contextPath %>/update.th?bno=<%= b.getBoardNo() %>" class="btn btn-warning btn-sm">수정하기</a>
			<button onclick="deleteThumb();" class="btn btn-danger btn-sm">삭제하기</button>
		</div>
	</div>
	
	<script>
		function deleteThumb(){
			if(confirm("정말 삭제하시겠습니까?")){
				location.href = "<%= contextPath %>/delete.th?bno=<%= b.getBoardNo() %>";
			} else{
				return;
			}
		}
	</script>
	
</body>
</html>