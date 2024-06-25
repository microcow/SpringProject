<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
	<sec:authorize access="isAuthenticated()">
			<sec:authentication property="principal.username" var="username"/>
    <h1>게시글 상세 정보</h1>
    <table>
        <tr>
            <th>작성자</th>
            <th>제목</th>
            <th>내용</th>
        </tr>
        <tr>
            <td>${board.bWriter}</td>
            <td>${board.bTitle}</td>
            <td>${board.bContent}</td>
        </tr>
    </table>
    <a href="/correctionboard?bId=${board.bId}&username=${username }">게시글 수정</a>
    <a href="/deleteboard?bId=${board.bId}&username=${username }">게시글 삭제</a>
    </sec:authorize>
	</div>
</body>
</html>