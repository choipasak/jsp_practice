<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- session은 자바의 코드여서 user_id를 얻으려면 특수기호를 사용해 자바코드를 적어야한다. -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- 	<%
 		/*
 		 내장 객체 session에서 데이터를 가져오는 메서드는 getAttribute() 입니다.
 		 매개값으로 가져올 세션 데이터의 이름을 적습니다.
 		 해당 데이터가 존재하지 않는다면 null이 리턴됩니다.
		 
 		 세션에 저장한 데이터는 브라우저 창이 종료될 때 까지, 혹은
 		 세션의 유효시간이 만료되기 전까지 웹 어플리케이션의 모든 jsp 파일에서 사용이 가능합니다.
 		 세션의 기본 유효시간은 30분입니다.
 		*/
  		String id = (String) session.getAttribute("user_id");//굳이 이렇게 쓸 필요가 없음 EL를 사용하면 더 간단해짐
		
 		if(id == null){//아이디가 null이라면
 			response. sendRedirect("session_login.jsp");
 		}else{
		%> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%--<h2>%=id%>님 환영합니다!</h2> 이렇게 'j s p'태그를 사용하지 않아도 된다.
	<!-- 이렇게 EL을 이용해서 사용 가능함. -> 내장객체를 꺼내오는 데 용이
		앞에 session까지도 안 써도 된다.
		알아서 request랑 response랑 뒤져서 user_id를 찾아서 가져옴.
		  --%>
	<c:if test="${user_id == null }">
		<script>
		  	alert('로그인 하지 않은 유저는 접근할 수 없습니다.');
			location.href='session_login.jsp'
		</script>
	</c:if>
	<h2>${user_id}님환영합니다!</h2>
	
	<a href="/JspBasic/session/Login" >로그아웃하기</a>
	<!-- a태그는 기본적으로 get을 요청한다. -> doget메서드를 호출하게 된다. -->


</body>
</html>
<%-- <% } %> --%>