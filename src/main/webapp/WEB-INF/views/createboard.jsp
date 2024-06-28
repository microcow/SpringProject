<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
			
			<c:if test="${not empty p_bId}">
			<h3><a href="/boarddetail?bId=${p_bId}" >원글 이동</a></h3> 
			</c:if><!-- p_bId값을 전달받았을 경우(답글일 경우)에만 노출 -->
			
				<form action="/insertboard" method="post" enctype="multipart/form-data"> 
					<!-- enctype="multipart/form-data 파일 업로드를 위해 폼의 enctype 속성을 반드시 이와 같이 설정해야 합니다. -->
					<p> 작성자 : ${username }님</p>
					<p> 제목 : <input type="text" name="bTitle"></p>
					<p> 내용 : <input type="text" name="bContent"></p>
					<h3>첨부 파일</h3>
					<input type="file" name="file">
					<input type="hidden" name="bWriter" value="${username }">
					
					<c:if test="${not empty momBoard.bId}"> <!-- 답글일 경우에만 실행 -->
					<input type="hidden" name="p_board" value="${momBoard.p_board }"> <!-- p_bId값을 전달받았을 경우(답글일 경우) 원글의 p_board값을 전달 -->
					<input type="hidden" name="depth" value="${momBoard.depth }"> <!-- p_bId값을 전달받았을 경우(답글일 경우) 원글의 depth값을 전달 -->
					<input type="hidden" name="grpord" value="${momBoard.grpord }"> <!-- p_bId값을 전달받았을 경우(답글일 경우) 원글의 grpord값을 전달 -->
					</c:if>
					
					<p> <input type="submit" value="작성하기"></p>
				</form>
			</sec:authorize>
		</div>
	</body>
</html>