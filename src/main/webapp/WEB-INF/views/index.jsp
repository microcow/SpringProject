<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 
<%
// taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" : c태그 (<c:forEach) 사용하기 위한 선언)
// taglib prefix="sec" uri="http://www.springframework.org/security/tags" : 이 JSP 페이지는 JSTL 코어 태그와 Spring Security 태그 라이브러리(sec:authorize ,sec:authentication 등)을 사용하기 위함
%>
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
               <h2>${principal }</h2>
               </sec:authorize>
               <%// ${principal}은 Spring Security에서 현재 인증된 사용자(principal)의 정보를 가져오기 위한 표현식입니다.
                 // 즉, 현재 접속 중인 세션의 정보를 가져오는거임 (상세 설명은 Spring Security를 사용하여 인증된 사용자의 정보를 JSP에서사용하는 방법 정리내용 참고)
                 // ★ <sec:authorize> 태그의 access 속성을 사용하여 접근 제어를 설정하면, 그 내부에서 <sec:authentication> 태그의 property 속성을 사용하여 사용자 인증(principal) 정보에 접근할 수 있습니다.  %>
                 
           
            
        </div>
        
        <div>
         <sec:authorize access="isAuthenticated()">
         <sec:authentication property="principal.username" var="username"/> 
         <h2>${username }님 환영합니다.</h2>      
               <a href="/user/info">내 정보</a>
               <a href="/admin">관리자</a>
               <a href="/createboard">게시글 작성</a>
               <a href="/boardlist">게시글 목록</a> <!-- 동일한 Jsp명이 있더라도 Java 컨트롤러 메서드가 호출된다 -->
            </sec:authorize>
        </div>   
</body>
</html>