<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	#enroll-form table{border:1px solid white;}
	#enroll-form input, #enroll-form textarea{
		width:100%;
		box-sizing:border-box;
	}
</style>
</head>
<body>
	<%@ include file="../common/menubar.jsp" %>
	
	<div class="outer">
		<br>
		<h2 style="text-align:center;">사진게시판 작성하기</h2>
		<br>
		<form action="<%= contextPath %>/insert.th" id="enroll-form" method="post" enctype="multipart/form-data">
			<table align="center">
				<tr>
					<th width="100">제목</th>
					<td colspan="3"><input type="text" name="title" required></td>
				</tr>
				<tr>
					<th>내용</th>
					<td colspan="3">
						<textarea name="content" style="resize:none;" rows="5" required></textarea>
					</td>
				</tr>
				<tr>
					<th>대표이미지</th>
					<td colspan="3" align="center"> <!-- 미리보기 -->
						<img id="titleImg" width="250" height="170">
					</td>
				</tr>
				<tr>
					<th>상세이미지</th>
					<td>
						<img id="contentImg1" width="150" height="120">
						<img id="contentImg2" width="150" height="120">
						<img id="contentImg3" width="150" height="120">
					</td>
				</tr>
			</table>
			<div id="file-area" style="display:none;">
				<input type="file" id="file1" name="file1" onchange="loadImg(this, 1);" required>
				<input type="file" id="file2" name="file2" onchange="loadImg(this, 2);">
				<input type="file" id="file3" name="file3" onchange="loadImg(this, 3);">
				<input type="file" id="file4" name="file4" onchange="loadImg(this, 4);">
			</div>
			<script>
				$(function(){
					$("#titleImg").click(function(){
						$("#file1").click();
					});
					
					$("[id^=contentImg]").each(function(index, item){
						$(item).click(function(){
							$("#file"+(index+2)).click();
						});
					});
				});
				
				function loadImg(inputFile, num) {
					// inputFile : 현재 변화가 생긴 input type="file" 요소
					// num : 몇 번째 input 요소인지 확인 후 해당 영역에 미리 보기 하기 위한 변수
					
					//console.log(inputFile.files.length);
					/*
						파일 선택 시 length = 1, 파일 선택 취소 시 length = 0 배열 안의 내용이 비어있게 됨
						length 값을 가지고 파일의 존재 유무를 알 수가 있다.
						
						files 속성은 업로드된 파일의 정보들을 "배열" 형식으로 여러 개 묶어서 변환, length는 그 배열의 크기를 의미
					*/
					if(inputFile.files.length != 0){
						// 선택된 파일이 존재할 경우에 선택된 파일들을 읽어들여서 그 영역에 맞는 곳에 미리 보기 추가
						
						// 파일을 읽어들일 FileReader 객체 생성
						let reader = new FileReader();
						
						// 파일을 읽어들이는 메서드 -> 어느 파일을 읽을지 매개 변수에 제시해 줘야 함
						// 0번째 인덱스에 담긴 파일 정보를 제시
						// -> 해당 파일을 읽어들이는 순간 해당 파일만의 고유한 url 부여됨
						// -> 해당 url을 src 속성값으로 제시
						reader.readAsDataURL(inputFile.files[0]);
						
						// 파일 읽기가 완료되었을 때 실행할 함수 정의
						reader.onload = function(e) { // e.target.result에 고유한 url 부여됨
							// 각 영역에 맞춰서 이미지 미리 보기 기능 제시
							let url = e.target.result;
							
							switch(num){
								case 1 : $("#titleImg").attr("src", url); break;
								case 2 : $("#contentImg1").attr("src", url); break;
								case 3 : $("#contentImg2").attr("src", url); break;
								case 4 : $("#contentImg3").attr("src", url); break;
							}
						}
					} else {
						// 선택된 파일이 없을 경우 미리 보기도 함께 사라지게끔 작업
						switch(num){
							case 1 : $("#titleImg").removeAttr("src"); break;
							case 2 : $("#contentImg1").removeAttr("src"); break;
							case 3 : $("#contentImg2").removeAttr("src"); break;
							case 4 : $("#contentImg3").removeAttr("src"); break;
						}
					}
				}
			</script>
			<div align="center">
				<button type="submit">등록하기</button>
			</div>
		</form>
	</div>
</body>
</html>