<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h2>${boardNo}번 게시물 내용 수정</h2>
	<!--  -->
	<form action="/JspBasic/update.board" method="post">
		<!-- 
		글번호를 넘기고 싶어. 그래야 글목록에 업데이트한걸로 바꿀수있지!
		form태그 안에 게시글의 인덱스번호가 있어야 전송이 가능한데,
		post 방식이어서 쿼리스트링으로 전달해줘도 보이지가 않음 그럼 어떡함
		(get방식 이었으면 주소 뒤에 뭍혀서 보낼 수 있음)
		 -->
		<input type="hidden" name="boardNo" value="${boardNo}" readonly/>
		<p>
		# 작성자: <input type="text" name="writer" value="${article.writer}"/> <br>
		# 제목: <input type="text" name="title" value="${article.title}" /> <br>
		# 내용: <textarea rows="3" name="content">${article.content}</textarea>
		<input type="submit" value="수정" />
	</p>
	</form>
</body>
</html>