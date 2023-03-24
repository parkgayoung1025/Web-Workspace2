<%@ page import="com.kh.member.model.vo.Member" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath(); // : /jspproject

	Member loginUser = (Member)session.getAttribute("loginUser");
	// 로그인 전 : null값이 담김
	// 로그인 후 : 로그인한 회원의 Member 객체

	String alertMsg = (String)session.getAttribute("alertMsg");
	// 모든 서비스 요청 전 : null;
	//		  요청 성공 : alert으로 띄워줄 메세지 문구 ex)로그인에 성공했습니다. , 게시글 등록에 성공했습니다.
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome C Class</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<!-- Popper JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<style>
	#login-form, #user-info{float: right}
	#user-info a{
		text-decoration: none;
		color: black;
		font-size: 12px;
	}
	.nav-area{background: black;}
	.menu{
		display: table-cell; /* 인라인 요소처럼 배치 가능 */
		height: 50px;
		width: 150px;
	}
	.menu a{
		text-decoration: none;
		color: white;
		font-size: 20px;
		font-weight: bold;
		display: block;
		width: 100%;
		height: 100%;
		
		line-height: 50px; /* 위 아래에서 가운데로 조정 */
	}
	.menu a:hover{
		background: darkgray;
	}
	.outer{
	background: black;
	color: white;
	width: 1000px;
	margin: auto;
	margin-top: 50px;
	}
</style>
</head>
<body>
	<script>
		const msg = "<%= alertMsg %>";
		
		if(msg != "null"){ // "성공적으로 로그인이 되었습니다." / "null"
			alert(msg);
			// 알람창을 띄워준 후 session에 담긴 메세지는 지워줘야함
			// 안그러면 menubar.jsp가 로딩될때마다 매번 alert함수가 실행됨
			<% session.removeAttribute("alertMsg"); %>
		}
	</script>

	<h1 align="center">Welcome C Class</h1>
	
	<div class="Login-area">
	
		<% if(loginUser == null) { %>
		<!-- 로그인하기 이전에만 보여지는 로그인 form -->
		<form id="login-form" action="<%= request.getContextPath() %>/login.me" method="post">
			<table>
				<tr>
					<th>아이디 : </th>
					<td><input type="text" name="userId" required></td>
				</tr>
				<tr>
					<th>비밀번호 : </th>
					<td><input type="password" name="userPwd" required></td>
				</tr>
				<tr>
					<th colspan="2">
						<input type="checkbox" id="saveId"> <label for="saveId">아이디 저장</label>
<!-- 						<button>로그인</button> -->
						<button type="button" onclick="submitLogin();">로그인</button>
						<button type="button" onclick="enrollPage();">회원가입</button>
					</th>
				</tr>
			</table>
		</form>
		<script>
			function enrollPage() {
				// location.href = "<%= contextPath %>/views/member/memberEnrollForm.jsp";
				// 웹애플리케이션의 디렉토리 구조가 url에 노출되면 보안에 취약
				
				// 단순한 정적인 페이지 이동요청이라고해도 반드시 sevlet을 거쳐갈것 => url의 서블릿 매핑값만 노출되도록 하기
				location.href = "<%= contextPath %>/enrollForm.me";
			}
			
			// 아이디저장 체크박스 클릭 시 쿠키값 저장
			function submitLogin(){
				
				let userId = $("#login-form input[name=userId]").val();
				
				if($("#saveId").is(":checked")) { // true -> 체크된 상태
					document.cookie = "saveId="+userId+"; path=/; max-age="+60*60*24; // 쿠키최대 유지시간 설정(1일)
				} else{ // 체크되지 않고 로그인시, 저장된 쿠키를 삭제
					document.cookie = "saveId=; path=/; max-age=0" // 최대시간을 0으로 설정해서 해당쿠키를 제거해주기
				}
				
				$("#login-form").submit();
			}
			
			// 로그인 전 아이디 저장 (쿠키에 값이 있는 경우에만)
			function getCookie(){
				let value = "";
				if(document.cookie.length > 0){
					let index = document.cookie.indexOf("saveId="); // 찾고자하는 쿠키인덱스의 시작값을 얻어옴
					if(index != -1){ // saveId라는 키값의 쿠키가 있다면
						index += "saveId=".length;
						let end = document.cookie.indexOf(";", index); // saveId=가 존재하는 시작위치로부터 ;를 찾겠고 그 인덱스값
						
						if(end == -1){ // saveId= 의 키값이 마지막위치라면 ;이 없으므로
							value = document.cookie.substring(index);
						}else{
							value = document.cookie.substring(index, end);
						}
						$("#login-form input[name=userId]").val(value);
						$("#saveId").attr("checked",true);
					}
				}
			}
			// 페이지가 로딩되었을 때 getCookie()함수 실행되게끔
			$(function(){
				getCookie();
			})
		</script>
		
		<% } else { %>
			<!-- 로그인 성공 후 -->
			<div id="user-info">
				<b><%= loginUser.getUserName() %></b>님 환영합니다. <br><br>
				<div align="center">
					<a href="<%= contextPath %>/mypage.me">마이페이지</a>
					<a href="<%= contextPath %>/logout.me">로그아웃</a> <!-- a태그는 무조건 get방식 -->
				</div>
			</div>
		<% } %>
	</div>
	
	<br clear="both"> <!-- float 속성 해제 -->
	<br>
	
	<div class="nav-area" align="center">
		<div class="menu"><a href="<%= contextPath %>">HOME</a></div>
		<div class="menu"><a href="<%= contextPath %>/list.no">공지사항</a></div>
		<div class="menu"><a href="<%= contextPath %>/list.bo?currentPage=1">일반게시판</a></div>
		<div class="menu"><a href="<%= contextPath %>/list.th">사진게시판</a></div>
	</div>

</body>
</html>