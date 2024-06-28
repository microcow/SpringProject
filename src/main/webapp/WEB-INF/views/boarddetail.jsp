<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
            <td>${board.bWriter}</td> <!-- 변수들의 Getter 메서드를 호출하고있음 -->
            <td>${board.bTitle}</td>
            <td>${board.bContent}</td>
        </tr>
    </table>
    <a href="/correctionboard?bId=${board.bId}&username=${username }">게시글 수정</a>
    <p><a href="/deleteboard?bId=${board.bId}&username=${username }">게시글 삭제</a></p>
    <p><a href="/createboard?bId=${board.bId}&username=${username }">답글 달기</a></p>
    
  <!-- 아래부터 댓글 --> 
	<p><button type="button" class="btnComment2">댓글달기</button></p>
	<!-- input방식은 폼을 서버로 전송하고자 하기 때문에 아래 skript의 e.preventDefault();가 작동하지 않을 수 있음 따라서 button 사용 -->
<table>
	<tr style="display: none;">
  	    <td colspan="4">
       		<textarea id="commentTextarea1" rows="2" cols="80"></textarea> <!-- textarea의 id는 다른 textarea의 id와 동일할 경우 제대로 전달되지 않을 수 있음 -->
       		<button type="button" class="btnCommentProc">등록</button>
		</td>
	</tr>
</table>

<h2>댓글</h2>
<table id="commentList">
	<tr>
	    <th>작성자</th>
	    <th>댓글 내용</th>
	    <th>작성일</th>
	</tr>
    <c:forEach items="${replyList}" var="reply" > <!-- "replyList"를 java에서 전달받고 reply에 foreach로 저장함 -->
    <tr> 
         <td>${reply.writer}</td>
         <td>${reply.content}</td>
         <td>${reply.date}</td>
         <td>
	         <form action="creat-reply.do" method="post">
				<input type="hidden" name="b_idx" value="${board2.b_idx}">
				<input type="hidden" name="r_idx" value="${reply.r_idx}">
				<p><button type="button" class="btnComment">댓글달기</button></p>
				<p><button type="button" class="btnChange">수정하기</button></p>
				<p><button type="button" class="btnDelete">삭제하기</button></p>
				<!-- e.preventDefault();로 인해 버튼을 클릭하더라도 creat-reply.do로 이동하지 않음 -->
			</form>
		 </td>
    </tr>
	<tr style="display: none;">
      	<td colspan="4">
   			<textarea id="commentTextarea2" rows="2" cols="80"></textarea>
   			<button type="button" class="btnCommentProc" grpord = "${reply.grpord}" p_post="${reply.p_post}" rIdx="${reply.r_idx}">등록</button> 
   			<!-- rIdx라는 속성을 직접 만든 후 r_idx값을 저장 -->
   			<!-- 댓글달기를 눌었을 때와 등록버튼을 눌렀을 때 이동되는 script는 다르기때문에 등록하기를 눌렀을 때 reply.r_idx를 따로 저장하지 않으면 불러올 수 없음 -->
      	</td>
	</tr>
	<tr style="display: none;">
      	<td colspan="4">
   			<textarea id="commentChange" rows="2" cols="80"></textarea>
   			<button type="button" class="btnChangeProc" rIdx="${reply.r_idx}">수정</button> 
   			<!-- rIdx라는 속성을 직접 만든 후 r_idx값을 저장 -->
   			<!-- 댓글달기를 눌었을 때와 등록버튼을 눌렀을 때 이동되는 script는 다르기때문에 등록하기를 눌렀을 때 reply.r_idx를 따로 저장하지 않으면 불러올 수 없음 -->
      	</td>
	</tr>
	<tr style="display: none;">
      	<td colspan="4">
   			<button type="button" class="btnDeleteProc" rIdx="${reply.r_idx}">삭제</button> 
   			<!-- rIdx라는 속성을 직접 만든 후 r_idx값을 저장 -->
   			<!-- 댓글달기를 눌었을 때와 등록버튼을 눌렀을 때 이동되는 script는 다르기때문에 등록하기를 눌렀을 때 reply.r_idx를 따로 저장하지 않으면 불러올 수 없음 -->
      	</td>
	</tr>
 	</c:forEach>
</table>


    <h2><a href="/boardlist">돌아가기</a></h2>
    </sec:authorize>
	</div>
	
<script>
$(document).on('click', '.btnComment2', function(e) {
    e.preventDefault();
    $(this).closest('table').find('tr').css('display', '');
});
</script>

</body>
</html>