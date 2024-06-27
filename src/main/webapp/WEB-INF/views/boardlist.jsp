<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>게시글 목록</title>
</head>
<body>
     <h1>게시글 목록</h1>
      <table>
      	<c:forEach var="list" items="${list }">
	      	<tr>
	   		   	<td>
	 		     	<a href="/boarddetail?bId=${list.bId}"> <!-- get 방식으로 값 전송 -->
	   		   			${list.bTitle }
	 		     	</a>
	      		</td>
	      	</tr>
      	</c:forEach>
      </table>
      <h2><a href="/"> 돌아가기 </a></h2>
</body>
</html>