<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js">
// jQuary 라이브러리 추가 코드
// 추가하지 않을 경우 Ajax나 script, jquery 사용에 문제가 발생할 수 있음
</script>

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

<h2>댓글 작성</h2>
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
				<p><button type="button" class="btnDelete" r_id = "${reply.r_id }" r_writer= "${reply.r_writer }">삭제하기</button></p>
			</form>
		 </td>
    </tr>
	<tr style="display: none;">
      	<td colspan="4">
   			<textarea id="commentTextarea2" rows="2" cols="80"></textarea>
   			<button type="button" class="btnComment2" r_grpord = "${reply.r_grpord}" p_rp="${reply.p_rp}" r_depth="${reply.r_depth}">등록</button> 
      	</td>
	</tr>
	<tr style="display: none;">
      	<td colspan="4">
   			<textarea id="commentTextarea3" rows="2" cols="80"></textarea>
   			<button type="button" class="btnComment3" r_id = "${reply.r_id}" >수정</button> 
   	
      	</td>
	</tr>
 	</c:forEach>
</table>


    <h2><a href="/boardlist">돌아가기</a></h2>
    </sec:authorize>
	</div>
	
<script>

$(document).on('click', '.btnCommentProc', function () { /// 최초 댓글 '등록'버튼 누를 시 여기로 (원댓글)
	/* let r_idx = $(this).parent().parent().prev().find('input[name="r_idx"]').val(); // input 요소 중에서 name 속성이 "r_idx"인 요소를 찾습니다.(r_idx를 직접 찾아가는 방법)
	.val(): jQuery에서 제공하는 메서드로, 폼 요소 (예: <input>, <textarea>, <select> 등)의 현재 값을 가져오거나 설정할 때 사용됩니다.
	값 가져오기: $('#commentTextarea').val()은 ID가 commentTextarea인 요소의 현재 값을 반환합니다.
	값 설정하기: $('#commentTextarea').val('new value')은 ID가 commentTextarea인 요소의 값을 'new value'로 설정합니다.*/
	
	let comment1 = $(this).closest('td').find('textarea').val(); // comment1을 위치로 불러오는법 (여기선 해당 ajax를 쓰는 textarea가 두개이므로 위치로 불러오는법 사용)
	// let comment1 = $('#commentTextarea2').val(); // comment1을 ID로 불러오는법 
	let b_id = ${board.bId};
	let username = '${username }';
	
	$.ajax({ // 페이지를 새로 고치지 않고도 데이터를 동적으로 로드하거나 전송
        url: "/reply", // click할 경우 url로 요청(호출) // 서버는 템플릿 파일을 로드하여 해당 내용을 응답으로 전송할 것입니다.
        type: "POST",
        data: { // 서버에 보낼 데이터는 data에 포함
        	r_content: comment1, // 저장된 내용(comment1 변수를 comment2라는 이름으로 보냄)
            r_writer: username,
            b_id: b_id
            
        },
        success: function(res){ // 요청이 성공(success)할 경우 아래 함수 실행 // 요청으로 부터 받은 응답 데이터가 res에 저장
            $('#commentList').html(res); // 요청이 성공하면 응답으로 받은 데이터를 #commentList라는 HTML 요소 내에 넣습니다.
            // 즉, #commentList라는 ID를 가진 태그 내부는 res라는 html로 교체된다
        },
        error: function(){
            $("#data").text("An error occurred");
        }
    });
});

$(document).on('click', '.btnComment', function(e) { /// 댓글의 '댓글달기' 누를 시 여기로
	e.preventDefault();
	$(this).closest('tr').next('tr').css('display', ''); // 태그 찾는법 : 이 코드는 선택한 요소를 기준으로 가장 가까운 <form> 요소를 찾고, 그 다음에 나오는 <table> 요소를 선택한 후, 그 안에 있는 모든 <tr> 요소를 선택하여 스타일을 변경합니다.
});

$(document).on('click', '.btnComment2', function () { /// 대댓글 '등록'버튼 누를 시 여기로 (대댓글)
	
	let comment1 = $(this).closest('td').find('textarea').val(); 
	let b_id = ${board.bId};
	let username = '${username }';
	let p_rp = $(this).attr('p_rp');
	let r_grpord = $(this).attr('r_grpord');
	let r_depth = $(this).attr('r_depth');
	
	$.ajax({
        url: "/reply",
        type: "POST",
        data: {
        	r_content: comment1,
            r_writer: username,
            b_id: b_id,
            r_depth: r_depth,
            p_rp: p_rp,
            r_grpord: r_grpord
            
        },
        success: function(res){
            $('#commentList').html(res);
            
        },
        error: function(){
            $("#data").text("An error occurred");
        }
    });
});



$(document).on('click', '.btnChange', function(e) { /// 댓글의 '수정하기' 누를 시 여기로
	e.preventDefault();
	$(this).parent().parent().parent().parent().next().next().css('display', '');
});

$(document).on('click', '.btnComment3', function () { /// 대댓글 '수정'버튼 누를 시 여기로
	
	let comment1 = $(this).closest('td').find('textarea').val(); 
	let r_id = $(this).attr('r_id');;
	let b_id = ${board.bId};
	
	$.ajax({
        url: "/changeReply",
        type: "POST",
        data: {
        	r_content: comment1,
            r_id: r_id,
            b_id: b_id
            
        },
        success: function(res){
            $('#commentList').html(res);
            
        },
        error: function(){
            $("#data").text("An error occurred");
        }
    });
});


$(document).on('click', '.btnDelete', function(e) { /// 댓글의 '삭제하기' 누를 시 여기로
	e.preventDefault();
	
	 if (confirm("정말 삭제하시겠습니까?")) { // confirm : 사용자가 대화상자에서 확인을 클릭하면 true, 취소를 클릭하면 false를 반환
	         
			//let r_id = '${reply.r_id}'; //제대로 값이 안불러와짐 : reply는 forEach문에 의해 추출된 값이기 때문에 r_id값을 다른데 저장해두지 않으면 값이 안불러와지는게 정상
			 
		 	let r_id = $(this).attr('r_id');;
	     	// 이미 정의된 값을 경로를 통해 가져오는법 1 (r_id 값 가져오기)
	     	
	        /*
	        let form = $(this).closest('form');
	        let r_id = form.find('input[name="r_id"]').val(); 
	        // jQuery를 사용하여 HTML 폼 요소에서 r_id라는 이름을 가진 <input> 요소의 값을 가져오는 코드
	        // 이미 정의된 값을 경로를 통해 가져오는법 2 (r_id 값 가져오기)
	        */
	  
	        let session = '${username }'; //따옴표 생략 가능
	        let b_id = ${board.bId};
	        let r_writer = $(this).attr('r_writer'); 
	        
	       
	      	  if(r_writer !== session){ //글 작성자와 현재 로그인 중인 유저의 이름 비교
	        	console.log("현재 사용자: " + session);
	        	console.log("r_writer: " + r_writer);
	        	console.log("r_id: " + r_id);
	        	console.log("b_id: " + b_id);
	        	alert("권한이 없습니다.");
	        	return;
	  	      }
	        
	        $.ajax({
	            url: "/deleteReply",
	            type: "POST",
	            data: {
	                r_id: r_id,
	                b_id: b_id
	            },
	            success: function(res) {
	                $('#commentList').html(res);
	            },
	            error: function() {
	                alert("삭제 중 오류가 발생했습니다.");
	            }
	        });
	    }
	});

</script>

</body>
</html>