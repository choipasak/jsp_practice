<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 사용자의 입력값을 받은 토대로 객체를 생성해보자! -->
	<form action="/JspBasic/el/obj">
		# 아이디: <input type="text" name="id"> <br>
		# 비밀번호: <input type="password" name="pw"> <br>
		# 이름: <input type="text" name="name"> <br>
		# 이메일: <input type="text" name="email"> <br>
		<input type="submit" value="확인">
	</form>

</body>
</html>