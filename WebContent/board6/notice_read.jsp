<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>

<script>

	function onDownload(num) {
	
	var o = document.getElementById("ifrm_filedown");

	o.src = "board6download.do?num=" + num;

	}
	
	function myInsert2(myIns2, myUp2) {
			
			let insertDiv = document.getElementById(myIns2);
			let updateBtn = document.getElementById(myUp2);
				if (insertDiv.style.display === "none") {
					if(updateBtn != null){
					updateBtn.setAttribute( 'disabled', 'true');	
					}
					insertDiv.style.display = "block";
				} else {
					if(updateBtn == null){
					updateBtn.removeAttribute('disabled');
					}
					insertDiv.style.display = "none";
			}
							
		}
	
	function myUpdate2(myUp2, myIns2) {

		var x = document.getElementById(myUp2);
		var y = document.getElementById(myIns2);
		if (x.style.visibility === "hidden") {
			x.style.visibility = "visible";
			y.style.visibility = "hidden";
		} else {
			x.style.visibility = "hidden";
			y.style.visibility = "visible";
		}
		
	}
	
</script>
<title>Insert title here</title>
</head>
<body>

<iframe id="ifrm_filedown" style="position:absolute; z-index:1; visibility:hidden;"></iframe>

	<div class="jumbotron">
		<div class="container">
			<h1>공지사항</h1>
			
			<div align="right">
				<c:if test="${login.id == 'adm'}">
					<a class="btn btn-outline-primary btn-sm" href="board6notice_updateui.do?num=${dto.num}">수정</a>
					<a class="btn btn-outline-primary btn-sm" href="board6notice_delete.do?num=${dto.num}" onclick="return confirm('삭제하시겠습니까?');">삭제</a>
				</c:if>
				<a class="btn btn-outline-primary btn-sm" href="board6notice_list.do">목록</a>
			</div>

		</div>
	</div>
	<div class="container" style="margin-bottom: 350px">
		<h3>${dto.title}</h3>
		<p>
			<strong>${dto.writer} </strong>&nbsp;&nbsp;&nbsp; ${dto.writeday} &nbsp;&nbsp;&nbsp; 조회수 : ${dto.readcnt}
		</p>

		<c:choose>
		
			<c:when test="${empty dto.filename}">	
							
			</c:when>
		
			<c:otherwise>
				<p align="right">
					<a href="board6notice_filedownload.do?num=${dto.num}" onclick="onDownload('${dto.num}')">첨부 파일 : ${dto.filename}</a>
				</p>
			</c:otherwise>
		</c:choose>
		<p>
			<br>${dto.content}
		</p>
	</div>

	<c:forEach items="${cList}" var="cl">
		<div class="container">
			<c:forEach begin="1" end="${cl.repIndent}">
				<!-- 들여쓰기 -->
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</c:forEach>
			<strong> ${cl.comment_writer} </strong> <br>
			<c:forEach begin="1" end="${cl.repIndent}">
				<!-- 들여쓰기 -->
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</c:forEach>
			${cl.comment_content} <br>
			<c:forEach begin="1" end="${cl.repIndent}">
				<!-- 들여쓰기 -->
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</c:forEach>
			${cl.comment_day }


			<!--				     -->
			<!-- 		대댓글달기     	 -->
			<!-- 				     -->
		<c:choose>
		
				<c:when test="${empty login}">	
					
				</c:when>
		
				<c:otherwise>
					<button class="btn btn-outline-primary btn-sm" id="myInserBtn${cl.comment_num}" onclick="myInsert2('myIns2${cl.comment_num}', 'myUpdateBtn${cl.comment_num}')">댓글달기</button>
					<c:if test="${login.id == cl.comment_writer}">
						<button class="btn btn-outline-primary btn-sm" id="myUpdateBtn${cl.comment_num}" onclick="myUpdate2('myUp2${cl.comment_num}', 'myInserBtn${cl.comment_num}')">수정</button>
						<a class="btn btn-outline-primary btn-sm" href="board6notice_cdelete.do?comment_board=${cl.comment_board}&comment_num=${cl.comment_num}&repRoot=${cl.repRoot}&repIndent=${cl.repIndent}" onclick="return confirm('삭제하시겠습니까?');">삭제</a>
					</c:if>
						<div id="myIns2${cl.comment_num}" style="display: none;">
							<form action="board6notice_reply.do" method="post">
								<input type="hidden" name="comment_num" value="${cl.comment_num}"> 
								<input type="hidden" name="comment_writer" value="${login.id}"> 
								<input type="hidden" name="comment_day" value="${cl.comment_day}"> 
								<input type="hidden" name="repRoot" value="${cl.repRoot}"> 
								<input type="hidden" name="repIndent" value="${cl.repIndent}"> 
								<input type="hidden" name="repStep" value="${cl.repStep}">
			
								<!-- 댓글입력 -->
								<input name="comment_board" type="hidden" value="${dto.num}">
								<textarea class="form-control" style="width: 30%;" name="comment_content" id="reply"></textarea>
								<input style="float: left" type="submit" value="등록" class="btn btn-outline-primary btn-sm">
							</form>
						</div>
						<div id="myUp2${cl.comment_num}" style="visibility: hidden">
							<form action="board6notice_cupdate.do" method="get">
								<input type="hidden" name="comment_num" value="${cl.comment_num}"> 
								<input type="hidden" name="comment_writer" value="${login.id}"> 
								<input type="hidden" name="comment_day" value="${cl.comment_day}"> 
								<input type="hidden" name="repRoot" value="${cl.repRoot}"> 
								<input type="hidden" name="repIndent" value="${cl.repIndent}"> 
								<input type="hidden" name="repStep" value="${cl.repStep}">
					
								<!-- 댓글입력 -->
								<input name="comment_board" type="hidden" value="${dto.num}">
								<textarea class="form-control" style="width: 30%;" name="comment_content" id="cupdate">${cl.comment_content}</textarea>
								<button style="float: left" type="submit" class="btn btn-outline-primary btn-sm">수정</button>
							</form>
						</div>
					
				</c:otherwise>
	
		</c:choose>
		</div>
		<br><br>
	</c:forEach>
	<!--				     -->
	<!-- 		댓글달기     	 -->
	<!-- 				     -->
	
	<c:choose>
	
		<c:when test="${empty login}">
		
		</c:when>
	
		<c:otherwise>
			<form action="board6notice_cinsert.do" method="get">
			<!-- 댓글입력 -->
			<div class="form-group" align="center">
				<textarea class="form-control" style="width: 30%;" name="comment_content"></textarea>
				<input name="comment_board" type="hidden" value="${dto.num}"> <br> <input type="hidden" name="comment_writer" value="${login.id}">
				<input type="submit" value="등록" class="btn btn-outline-primary btn-sm">
			</div>
			</form>
		</c:otherwise>
	
	</c:choose>

</body>
</html>