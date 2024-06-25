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
				<form action="/updateboard" method="post" enctype="multipart/form-data"> 
					<!-- enctype="multipart/form-data 파일 업로드를 위해 폼의 enctype 속성을 반드시 이와 같이 설정해야 합니다. -->
					<p> 작성자 : ${username }님</p>
					<p> 제목 : <textarea name="bTitle" >${board.bTitle}</textarea></p>
					<p> 내용 : <textarea name="bContent">${board.bContent}</textarea></p>
					<h3>첨부 파일</h3>
					<input type="file" name="file">
					<input type="hidden" name="bId" value="${board.bId}">
					<p> <input type="submit" value="수정하기"></p>
				</form>
			</sec:authorize>
		</div>
	</body>
</html>