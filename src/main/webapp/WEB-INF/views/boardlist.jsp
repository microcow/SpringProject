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
<form action="/searchBoard" method="post">
  <label for="lang">검색</label> <!-- label 키워드 -->
  <select name="search" id="lang">
  	<option value="select">search</option>
    <option value="b_title">제목</option>
    <option value="b_content">내용</option>
    <option value="b_title AND b_content">제목 + 내용</option>
    <option value="b_writer">작성자</option>
  </select>
  <input type="text" name="content" placeholder="검색어를 입력하세요.">
  <input type="submit" value="Submit" />
</form>
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