<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ajax 개요</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
	<h1>Ajax 개요</h1>
	<p>
		Asynchronous JavaScript And Xml의 약자<br>
		서버로부터 데이터를 가져와 전체 페이지를 새로고침하지 않고 일부만 로드할 수 있게 하는 기법<br>
		우리가 기존에 a 태그로 요청하거나 form을 통해 요청했던 방식은 동기식 요청 방식<br>
		=> 응답 페이지가 돌아와야 볼 수 있으며 응답 페이지가 호출되기 전까지 아무 요청하지 못함<br>
		비동기식 요청을 보내기 위해서는 AJAX라는 기술이 필요함
		<br><br>
		* 동기식 / 비동기식<br>
		- 동기식 : 요청 처리 후 그에 해당하는 응답 페이지가 돌아와야만 그다음 작업이 가능함<br>
		         만약 서버에서 호출된 결과까지의 시간이 지연되면 계속 기다려야 함(흰 화면으로 보이게 됨)<br>
		         전체 페이지가 리로드 됨(새로고침 즉, 페이지가 기본적으로 한 번 깜빡거림)
		<br><br>
		- 비동기식 : 현재 페이지를 그대로 유지하면서 중간중간 추가적인 요청을 보내줄 수 있음<br>
		          요청을 한다고 해서 다른 페이지로 넘어가지 않음(현재 페이지 유지)<br>
		          요청을 보내놓고 그에 해당하는 응답이 돌아올 때까지 현재 페이지에서 다른 작업을 할 수 있음<br>
		          ex) 아이디 중복 체크 기능, 검색어 자동 완성 기능<br>
		* 비동기식의 단점<br>
		- 현재 페이지에 지속적으로 리소스가 쌓임 => 페이지가 느려질 수 있음<br>
		- 페이지 내 복잡도가 크게 증가함 => 에러 발생 시 대처가 어려워질 수 있음<br>
		- 요청 후 들어온 응답 데이터를 가지고 현재 페이지에 새로운 요소를 만들어서 뿌려줘야 되는데,
		  DOM 요소를 새로이 만들어 내는 구문을 잘 숙지해야 함<br> 
	</p>
	<h1>javascript 방식을 이용한 Ajax 테스트</h1>
	
	<h3>1. 버튼 클릭 시 get 방식으로 서버에 데이터 전송 및 응답</h3>
	
	<h3>자바스크립트를 이용한 ajax 통신</h3>
	
	<button onclick="ajaxTest1();">JavaScript ajax 테스트 1</button>
	
	<div id="target"></div>
	
	<script>
		function ajaxTest1() {
			// ajax로 서버와 통신하기
			// 1. XMLHttpReqest 객체 생성하기
			const xhr = new XMLHttpRequest();
			
			// 2. XMLHttpRequest 객체를 설정하기
			// open() 함수를 이용해서 통신값을 설정
			// 매개 변수 1 : get / post, 매개 변수 2 : 요청을 보내는 주소, 매개 변수 3(생략 가능) : 동기식, 비동기식 설정
			// * 클라이언트가 보내는 값을 파라미터로 전송
			xhr.open("get", "<%= contextPath %>/ajaxTest.do?id=admin");
			
			// 3. 요청에 대한 서버 응답을 처리할 함수를 지정
			// xhr 객체의 onreadystatechange 속성에 이벤트 핸들러를 추가
			// xhr 객체의 상태를 관리하는 속성
			// 1) readystate : 전송 상태를 관리
			// 2) status : 응답 결과를 관리
			
			xhr.onreadystatechange = function() {
				if (xhr.readyState == 4) { // 응답 완료
					if (xhr.status == 200) { // 정상적으로 수신 완료
						// 서버가 응답한 내용은 xhr 객체의 responseText라는 속성에 자동으로 들어감
						console.log(xhr.responseText);
						document.getElementById("target").innerHTML += "<h3>"+xhr.responseText+"</h3>";
					} 
					else if (xhr.staus == 404) { // 찾는 페이지(url)가 존재하지 않는다.
						alert("찾는 페이지가 존재하지 않습니다.");
					} else { // 그 외 에러
						alert("에러가 발생했습니다.");
					}
				}
			}
			// 설정 완료 후 요청을 보냄(send)
			xhr.send();
		}
	</script>
</body>
</html>