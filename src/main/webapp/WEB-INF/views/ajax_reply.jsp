<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<table id="commentList">
	<tr>
	    <th>작성자</th>
	    <th>댓글 내용</th>
	    <th>작성일</th>
	</tr>
    <c:forEach items="${replyList}" var="reply" > <!-- "replyList"를 java에서 전달받고 reply에 foreach로 저장함 -->
    <tr> 
         <td>${reply.r_writer}</td>
         <td>${reply.r_content}</td>
         <td>${reply.r_date}</td>
 	</c:forEach>
 	<tr>
 	<td colspan="4">
       		<textarea id="commentTextarea1" rows="2" cols="80"></textarea> <!-- textarea의 id는 다른 textarea의 id와 동일할 경우 제대로 전달되지 않을 수 있음 -->
       		<button type="button" class="btnCommentProc">등록</button>
		</td>
	</tr>
</table>