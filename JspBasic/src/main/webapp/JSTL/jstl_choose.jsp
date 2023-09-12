<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<c:set var="age" value="${param.age}" />
	<!-- scope는 따로 지정하지 않겠다!(= page)
		 age를 사용하는 용도에 따라서 데이터 타입을 자동으로 변환해준다.
	 -->

	# 이름: ${param.name} <br>
	# 나이: ${age}세 <br>

	<!-- 위에서부터 차례차례 실행된다. -->
	<c:choose>
		<c:when test="${age >= 20}">
			<c:choose>
				<c:when test="${age >= 40}">
					<h3>장년층 입니다.</h3>
				</c:when>
				<c:otherwise>
					<h3>청년층 입니다.</h3>
				</c:otherwise>
			</c:choose>
		</c:when>
		<c:when test="${age >= 17}">
			<h3>당신은 고등학생입니다.</h3>
		</c:when>
		<c:when test="${age >= 14}">
			<h3>당신은 중학생입니다.</h3>
		</c:when>
		<c:when test="${age >= 8}">
			<h3>당신은 초등학생입니다.</h3>
		</c:when>
		<c:otherwise>
			<h3>당신은 미취학 아동입니다.</h3>
		</c:otherwise>
	</c:choose>
	<!-- choose를 열고 when, when, ... 원하는 만큼 조건의 분기를 말해준다! 
		 else역할을 하는 태그: otherwise
	-->
	
</body>
</html>