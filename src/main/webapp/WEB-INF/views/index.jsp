<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
     <h1>Home Page</h1>
        <hr>
        <div>
           <sec:authorize access="isAnonymous()"> <!-- isAnonymous()는 현재 사용자가 익명 상태인지를 검사하는 함수입니다. 즉, 로그인 되지 않았을때 보임 -->
           <!-- sec:authorize 태그는 Spring Security의 spring-security-taglibs 라이브러리를 사용하여
            JSP 파일에서 보안 관련 기능을 쉽게 구현할 수 있도록 도와줍니다. -->
              <a href="/login">로그인</a>
              <a href="/beforeSignUp">회원가입</a>
           </sec:authorize>
            <sec:authorize access="isAuthenticated()"> <!-- isAuthenticated()는 현재 사용자가 인증된 상태인지를 검사하는 함수. 즉, 로그인 되었을 때 보임 -->
               <a href="/logout">로그아웃</a>
               <sec:authentication property="principal" var="principal"/>
               <!-- 사용자의 principal 객체를 가져와 principal이라는 변수에 저장 -->
               <h2>${principal }</h2> <!-- principal은 어디서 가져오는것? -->
            </sec:authorize>
            
        </div>
        <div>
         <sec:authorize access="isAuthenticated()">        
               <a href="/user/info">내 정보</a>
               <a href="/admin">관리자</a>
            </sec:authorize>
        </div>   
</body>
</html>