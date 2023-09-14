<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h2>게시글 목록</h2>
	<c:choose>
		<c:when test="${boardList.size() == 0}"><!-- 객체는 이미 생성이 되서 값만 없는 것이기 때문에 == null을 하면 안된다! -->
			<h3>*** 작성된 게시물이 하나도 없습니다. ***</h3>
		</c:when>
		<c:otherwise>
			<table border="1">
				<tr>
					<th>번호</th>
					<th>작성자</th>
					<th>제목</th>
					<th>작성일</th>
					<th>비고</th>
				</tr>
				<!-- 반복문을 통해서 값을 꺼내 보여주기 -->
				<!-- c:forEach의 속성인 varStatus를 활용하면
					현재 사용중인 반복문의 여러가지 속성을 사용할 수 있습니다.
					${num.count}: 1부터의 순서
					${num.index}: 0부터의 순서
					${num.current}: 현재 아이템
				 -->
				<c:forEach var="b" items="${boardList}" varStatus="num">
					<tr>
						<td>${num.count}</td>
						<td>${b.writer}</td>
						<!-- getWriter()라고 작성하지 않아도 된다! writer라는 글씨를 보고 VO객체에서 알아서 getWriter()를 찾아서 가져와준거임. -->
						<td><a href="/JspBasic/content.board?bId=${num.count}">
								${b.title} </a> <!-- 이렇게 글의 번호를 같이 뭍혀서 보내야 한다. --></td>
						<td><fmt:parseDate value="${b.regDate}"
								pattern="yyyy-MM-dd'T'HH:mm:ss" var="parsedDateTime" type="both" />
							<fmt:formatDate value="${parsedDateTime}"
								pattern="yyyy년 MM월 dd일 HH시 mm분" /></td>
						<td><a href="/JspBasic/delete.board?bId=${num.count}"
							onclick="return confirm('정말 삭제하시겠습니까?')">[삭제]</a></td>
					</tr>
				</c:forEach>
			</table>
		</c:otherwise>
	</c:choose>
	<br>
	
	<form action="/JspBasic/search.board">
		<input type="text" name="keyword" placeholder="작성자 이름을 입력하세요."/>
		<input type="submit" value="검색" />	
	</form>
	
	<br>
	<a href="/JspBasic/write.board">새 글 작성하기</a>

</body>
</html>



















