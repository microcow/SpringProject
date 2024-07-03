<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<table id="commentList">
	<tr>
 		<td colspan="4">
       		<textarea id="commentTextarea1" rows="2" cols="80"></textarea> <!-- textarea의 id는 다른 textarea의 id와 동일할 경우 제대로 전달되지 않을 수 있음 -->
       		<button type="button" class="btnCommentProc">등록</button>
		</td>
	</tr>
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
         <td>
	         <form action="creat-reply.do" method="post">
				<input type="hidden" name="b_id" value="${reply.b_id}">
				<input type="hidden" name="r_id" value="${reply.r_id}">
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
   			<button type="button" class="btnComment2" r_grpord = "${reply.r_grpord}" p_rp="${reply.p_rp}" r_depth="${reply.r_depth}">등록</button> 
   			<!-- rIdx라는 속성을 직접 만든 후 r_idx값을 저장 -->
   			<!-- 댓글달기를 눌었을 때와 등록버튼을 눌렀을 때 이동되는 script는 다르기때문에 등록하기를 눌렀을 때 reply.r_idx를 따로 저장하지 않으면 불러올 수 없음 -->
      	</td>
	</tr>
 	</c:forEach>
</table>