<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
     <h1>Hello World</h1>
      <table>
      	<tr>
      		<td>id</td>
      		<td>title</td>
      		<td>content</td>
      		<td>writer</td>
      	</tr>
      	<c:forEach var="list" items="${list }">
	      	<tr>
	      		<td>${list.bId }</td>
	      		<td>${list.bTitle }</td>
	      		<td>${list.bContent }</td>
	      		<td>${list.bWriter }</td>
	      	</tr>
      	</c:forEach>
      </table>
</body>
</html>