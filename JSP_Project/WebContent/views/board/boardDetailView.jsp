<%@page import="com.kh.board.model.vo.Attachment"%>
<%@ page import="com.kh.board.model.vo.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Board b = (Board)request.getAttribute("b");
	Attachment at = (Attachment)request.getAttribute("at");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>일반게시판 상세페이지</title>
<style>
	.outer table{border-color: white;}
</style>
</head>
<body>
	<%@ include file="../common/menubar.jsp" %>
	
	<div class="outer">
		<br>
		<h2 align="center">일반게시판 상세보기</h2>
		<br>
		
		<table id="detail-area" align="center" border="1">
			<tr>
				<th width="70">카테고리</th>
				<th width="70"><%= b.getCategory() %></th>
				<th width="70">제목</th>
				<td width="350"><%= b.getBoardTitle() %></td>
			</tr>
			<tr>
				 <th>작성자</th>
				 <td><%= b.getBoardWriter() %></td>
				 <th>작성일</th>
				 <td><%= b.getCreateDate() %></td>
			</tr>
			<tr>
				<th>내용</th>
				<td colspan="3">
					<p style="height: 200px"><%= b.getBoardContent() %></p>
				</td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td colspan="3">
					<% if(at == null){ %>
						첨부파일이 없습니다.
					<% } else { %>
						<a download="<%= at.getOrignName() %>" href="<%= contextPath %>/<%= at.getFilePath()+at.getChangeName() %>"><%= at.getOrignName() %></a>
					<% } %>

				</td>
			</tr>
		</table>
	
		<br>
		
		<div align="center">
			<a href="<%= contextPath %>/list.bo?currentPage=1" class="btn btn-secondary btn-sm">목록가기</a>
			
			<!-- 로그인한 사용자가 해당 게시글의 작성자인 경우 -->
 			<% if(loginUser != null && loginUser.getUserId().equals(b.getBoardWriter())){ %>
				<a href="<%= contextPath %>/update.bo?bno=<%= b.getBoardNo() %>" class="btn btn-warning btn-sm">수정하기</a>
				<button onclick="deleteBoard();" class="btn btn-danger btn-sm">삭제하기</button>
<%-- 				<a href="<%= contextPath %>/delete.bo?bno=<%= b.getBoardNo() %>" class="btn btn-danger btn-sm">삭제하기</a> --%>
			<%} %>
		</div>
		
		<script>
			function deleteBoard(){
				if(confirm("정말 삭제하시겠습니까?")){
					location.href = "<%= contextPath %>/delete.bo?bno=<%= b.getBoardNo() %>";
				}else{
					return;
				}
			}
		</script>
		
		<br>
		<!-- 댓글기능 화면구현만 해두기, 요 아래는 작업 X -->
		
		<div id="reply-area">
			<table border="1" align="center">
				<thead>
					<% if(loginUser != null){ %>
						<!-- 로그인이 되어있을 경우 -->
						<tr>
							<th>댓글작성</th>
							<td>
								<textarea id="replyContent" rows="3" cols="50" style="resize: none;"></textarea>
							</td>
							<td><button onclick="insertReply();">댓글등록</button></td>
						</tr>
					<%} else{ %>
						<tr>
							<th>댓글작성</th>
							<td>
								<textarea id="replyContent" rows="3" cols="50" style="resize: none;" readonly>로그인 후 이용가능한 서비스입니다.</textarea>
							</td>
							<td><button onclick="insertReply();">댓글등록</button></td>
						</tr>
					<%} %>
				</thead>
				<tbody>
<!-- 					<tr>
						<td>user01</td>
						<td>테스트 댓글</td>
						<td>2023-02-20</td>
					</tr>
					<tr>
						<td>user01</td>
						<td>테스트 댓글</td>
						<td>2023-02-20</td>
					</tr>
					<tr>
						<td>user01</td>
						<td>테스트 댓글</td>
						<td>2023-02-20</td>
					</tr> -->
				</tbody>
			</table>
		</div>
	</div>
	<script>
		$(function(){
			// 실시간으로 댓글 입력시 화면에 바로 나타나게함
			setInterval(selectReplyList, 1000);
		});
	
		// 댓글 등록
		function insertReply(){
			$.ajax({
				url : "<%= contextPath %>/insertReply.bo",
				data : {
					content : $("#replyContent").val(),
					bno		: "<%= b.getBoardNo() %>"
				},
				success : function(result){
					console.log(result);
					
					// 댓글 등록 성공시 result = 1
					if(result > 0){
						// 새 댓글목록 불러오는 함수 호출
						selectReplyList();
						// 댓글 내용 비워주기
						$("#replyContent").val("");
						
					// 댓글 등록 실패시 result = 0
					}else{
						alert("댓글 등록 실패")
					}
				},
				error : function () {
					console.log("댓글 작성 실패")
				}
			})
		}
		
		// 댓글 리스트 조회
		function selectReplyList(){
			$.ajax({
				url : "<%= contextPath %>/replyList.bo",
				data : {bno : <%= b.getBoardNo() %>},
				success : function(list){
					console.log(list)
					// 서버로부터 전달받은 리스트를 반복문을 통해 댓글 목록으로 변환
					let result = "";

					for(let i of list){
						result += "<tr>"
									+"<td>" + i.replyWriter + "</td>"
									+"<td>" + i.replyContent + "</td>"
									+"<td>" + i.createDate + "</td>"
								+"</tr>";
					}
					$("#reply-area tbody").html(result);
				},
				error : function(){
					console.log("게시글 목록 조회 실패")
				}
			})
		}
		selectReplyList();
	</script>
</body>
</html>