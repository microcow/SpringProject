<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>글 작성하기</title>
</head>
	<body>
		<div>
		<sec:authorize access="isAuthenticated()">
			<sec:authentication property="principal.username" var="username"/>
				<form action="/insertboard" method="post" enctype="multipart/form-data"> 
					<!-- enctype="multipart/form-data 파일 업로드를 위해 폼의 enctype 속성을 반드시 이와 같이 설정해야 합니다. -->
					<p> 작성자 : ${username }님</p>
					<p> 제목 : <input type="text" name="bTitle"></p>
					<p> 내용 : <input type="text" name="bContent"></p>
					<h3>첨부 파일</h3>
					<input type="file" name="file">
					<input type="hidden" name="bWriter" value="${username }">
					<p> <input type="submit" value="작성하기"></p>
				</form>
			</sec:authorize>
		</div>
	</body>
</html>