<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>EL 연산</h2>
	
	<h3>1. 산술 연산</h3>
	
	<p>
		* 기존 방식 <br>
		10 + 3 = <%= (int)request.getAttribute("big") + (int)request.getAttribute("small") %>
	<!-- 문자열 -> 정수값으로 바꾸고 싶을 때는 Integer 사용 -->
	<!-- 객체 -> 정수갑으로 바꾸고 싶을 때는 int 사용 -->
	</p>
	
	<p>
		* EL 방식 연산 <br>
		10 + 3 = ${big + small} <br>
		10 - 3 = ${big - small} <br>
		10 X 3 = ${big * small} <br>
		10 / 3 = ${big / small} 또는 ${big div small} <br>
		10 % 3 = ${big % small} 또는 ${big mod small}
	</p>
	
	<h3>2. 숫자간 대소 비교 연산</h3>
	
	<p>
		* 기존 방식 <br>
		10 > 3 : <%= (int)request.getAttribute("big") > (int)request.getAttribute("small") %>
	</p>
	
	<p>
		* EL 방식 연산 <br>
		<!-- 대소 비교는 예약어 사용하는 걸 권장하는 편 -->
		10 > 3 : ${big > small} 또는 ${big gt small} <br>
		10 < 3 : ${big < small} 또는 ${big lt small} <br>
		10 >= 3 : ${big >= small} 또는 ${big ge small} <br>
		10 <= 3 : ${big <= small} 또는 ${big le small} 
	</p>
	
	<hr>
	
	<h3>3. 동등 비교 연산</h3>
	
	<p>
		* 기존 방식 <br>
		10괴 3이 일치합니까 ? : <%= (int)request.getAttribute("big") == (int)request.getAttribute("small") %>
		sOne과 sTwo가 일치합니까 ? : <%= ((String)request.getAttribute("sOne")).equals((String)request.getAttribute("sTwo")) %>
		                         또는 <%= (String)request.getAttribute("sOne") == (String)request.getAttribute("sTwo") %>
	</p>
	
	<p>
		* EL 방식 연산 <br>
		10과 3이 일치합니까 ? : ${big == small} 또는 ${biq eq small} <br>
		big에 담긴 값이 10과 일치합니까 ? : ${big == 10} 또는 ${big eq 10} <br>
		
		sOne과 sTwo가 일치합니까 ? : ${sOne eq sTwo} 또는 ${sOne == sTwo} <br>
		<!-- EL에서 == 비교는 자바에서의 equals()와 같은 동작을 함 -->
		
		sOne과 sTwo가 일치하지 않습니까? : ${sOne != sTwo} 또는 ${sOne ne sTwo} <br>
		<!-- EL에서 != 비교는 자바에서의 Not equals()와 같은 동작을 함 -->
		
		sOne에 담긴 값이 "안녕"과 일치합니까? : ${sOne == "안녕"} 또는 ${sOne eq '안녕'}
		<!-- EL에서 문자열 리터럴 제시 시 홀따옴표, 쌍따옴표 상관없다. -->
	</p>
	
	<h3>4. 객체가 null인지 혹은 list가 비어있는지 체크하는 연산</h3>
	
	<p>
		* EL 방식 연산 <br>
		pTwo가 null 입니까? : ${pTwo == null} 또는 ${empty pTwo} 또는 ${pTwo eq null} <br>
		pOne은 null 입니까? : ${pOne == null} 또는 ${empty pOne} 또는 ${pOne eq null} <br>
		pOne이 null이 아닙니까? : ${pOne != null} 또는 ${!empty pOne} <br>
		
		lOne이 비어있습니까? : ${empty lOne} <br>
		lTwo가 비어있습니까? : ${empty lTwo}
	</p>
	
	<h3>5. 논리 연산자</h5>
	
	<p>
		* EL 방식 연산 <br>
		AND 연산 : ${true && true} 또는 ${true and true} <br>
		OR 연산 : ${true || false} 또는 ${false or true} <br>
	</p>
	
	<h3>연습 문제</h3>
	
	<p>
		* EL 연산에서 배운 키워드만 가지고 써 보기 <br>
		big이 small보다 크고 lOne은 텅 비어있습니까? : ${big gt small and empty lOne} <br>
		big과 small의 곱은 4의 배수입니까? : ${big * small mod 4 eq 0} <br>
		lTwo가 텅 비어있지 않거나 또는 sOne에 담긴 값이 "안녕하쇼"와 일치합니까? : ${!empty lTwo or sOne eq "안녕하쇼"}
	</p>
</body>
</html>