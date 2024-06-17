<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h1>마이 페이지</h1>
	<hr>
        <div>
           <sec:authorize access="isAuthenticated()">
              <sec:authentication property="principal" var="principal"/>
               <h2>${principal }</h2>
           </sec:authorize>
           <a href="/">돌아가기</a>
        </div>
</body>
</html>