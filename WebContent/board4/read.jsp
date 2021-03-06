<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>글읽기</title>
<%@ include file="../com/head.jsp"%>
</head>

<body>
	<%@ include file="../com/top.jsp"%>
	<%@ include file="../com/navbar.jsp"%>

	<div class="container" style="margin: 30px auto 30px auto;">
		<h2>${dto.title }</h2>
		
		<hr>
		
		<h6 style="color: #666;">
		<%@ include file="../com/icon/biGeoAlt.jsp" %>&emsp;${dto.location }&emsp;&emsp;
		<%@ include file="../com/icon/biHouseDoorFill.jsp" %>&emsp;${dto.thema }&emsp;&emsp;
		<%@ include file="../com/icon/biPersonLinesFill.jsp" %>&emsp;${dto.writer }&emsp;&emsp;
		<%@ include file="../com/icon/biClockFill.jsp" %>&emsp;${dto.writeday }&emsp;&emsp;
		<%@ include file="../com/icon/biEyeFill.jsp" %>&emsp;${dto.readcnt }&emsp;&emsp;
		</h6><hr>
		
		<div class="fakeimg">
			<c:if test="${dto.filename ne null }">
				<img class="img-thumbnail" alt="${dto.filename }" src="upload/${dto.filename }">
			</c:if>
		</div>
		<hr>
		
		<p>${dto.content }</p><hr>
		
		<div class="row">
			<div class="col" style="text-align: left;">
				<c:if test="${login.id ne null}">
					<a class="btn btn-primary" href="board4replyui.do?num=${dto.num }">답글</a>
				</c:if>
				<a class="btn btn-secondary" href="board4list.do?curPage=1&location=000&thema=000">목록</a>
				<c:if test="${login.id eq dto.writer }">
					<a class="btn btn-warning" href="board4updateui.do?num=${dto.num }">수정</a>
					<a class="btn btn-danger" href="board4delete.do?num=${dto.num }">삭제</a>
				</c:if>
			</div>
			<div class="col" style="text-align: right;">
				<a class="btn btn-secondary ${dto.num eq 1 ? 'disabled' : ''}" href="board4read.do?num=${dto.num - 1 }">이전</a> <a class="btn btn-secondary ${totalNum eq dto.num ? 'disabled' : '' }" href="board4read.do?num=${dto.num + 1 }">다음</a>
			</div>
		</div>
	</div>

	<%@ include file="../com/footer.jsp"%>

</body>

</html>