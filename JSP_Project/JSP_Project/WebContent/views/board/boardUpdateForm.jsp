<%@page import="java.util.ArrayList, com.kh.board.model.vo.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ArrayList<Category> list = (ArrayList<Category>)request.getAttribute("list");
	Board b = (Board)request.getAttribute("b");
	Attachment at = (Attachment)request.getAttribute("at");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 수정 페이지</title>
<style>
	#update-form>table{border: 1px solid white;}
	#update-form input, #update-form textarea{
		width: 100%;
		box-sizing: border-box;
	}
</style>
</head>
<body>
	<%@ include file="../common/menubar.jsp" %>
	
	<div class="outer">
		<br>
		<h2 align="center">일반게시판 수정하기</h2>
		<br>
		
		<form action="<%= contextPath %>/update.bo" id="update-form" method="post" enctype="multipart/form-data">
			<input type="hidden" name="bno" value="<%= b.getBoardNo() %>">
			<!-- 카테고리, 제목, 내용, 첨부파일을 입력받고, 작성자정보(받아올 정보의 세션에 있는 데이터를 빼올것임) -->
			<table align="center">
				<tr>
					<!-- 
						DB로부터 카테고리 정보를 조회해서 보여주게끔 하는게 좋다.
						카테고리가 새롭게 추가되거나, 삭제되는 경우 해당 카테고리를 참조하고있는
						모든 JSP에 들어가서 수정해줘야하기 때문에
					 -->
					<th width="100">카테고리</th>
					<td width="500">
						<select name="category">
							<% for(Category c : list){ %>
								<option value="<%= c.getCategoryNo() %>"
								
<%--  								<% if(c.getCategoryName().equals(b.getCategory())){ %>
									selected="selected"
								<%} %> --%>
								
								><%= c.getCategoryName() %></option>
							<%} %>
							<!-- 내가 선택한 카테고리가 자동으로 선택되어 있도록 작업해주기(js활용) -->
						</select>
						
						<script>
							$(function(){
								$("#update-form option").each(function(){
									/*
										현재 반복을 진행중인 option 태그의 text값과
										db에서 가져온 categoryname값이 일치하는경우 선택되도록
									*/
									if($(this).text().trim() == "<%= b.getCategory() %>"){
										// 일치하는 경우에만 option태그를 선택상태로 변경
										$(this).attr("selected", true);
									}
								})
							});
						</script>
					</td>
				</tr>
				<tr>
					<th>제목</th>
					<td><input type="text" name="title" value="<%= b.getBoardTitle() %>" required></td>
				</tr>
				<tr>
					<th>내용</th>
					<td><textarea name="content" rows="10" required><%= b.getBoardContent() %></textarea></td>
				</tr>
				<tr>
					<th>첨부파일</th>
					<td>
						<% if(at != null){ %>
							<%= at.getOrignName() %>
							<!-- 원본파일의 파일번호, 수정명을 hidden으로 함께 전송할 예정 -->
							<input type="hidden" name="originFileNo" value="<%= at.getFileNo() %>" >
							<input type="hidden" name="changhFileName" value="<%= at.getChangeName() %>" >
						<% } %>
					<input type="file" name="upfile">
					</td>
				</tr>
			</table>
			
			<br>
			
			<div align="center">
				<button type="submit">수정하기</button>
			</div>
		</form>
	</div>

</body>
</html>