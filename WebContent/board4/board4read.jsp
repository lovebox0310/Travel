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
		<h2>글쓰기</h2>
		<form action="">
			<div class="form-group">
				<label for="location">지역:</label> <input class="form-control" id="location" name="location" value="${dto.location }" readonly="readonly"></input>
			</div>
			<div class="form-group">
				<label for="thema">태마:</label> <input class="form-control" id="thema" name="thema" value="${dto.thema }" readonly="readonly"></input>
			</div>
			<div class="form-group">
				<label for="writer">작성자:</label> <input type="text" class="form-control" id="writer" name="writer" value="${dto.writer }" readonly="readonly">
			</div>
			<div class="form-group">
				<label for="title">제목:</label> <input type="text" class="form-control" id="title" value="${dto.title }" name="title" readonly="readonly">
			</div>
			<div class="form-group">
				<label for="exampleFormControlTextarea1">내용:</label>
				<textarea class="form-control" id="exampleFormControlTextarea1" rows="3" name="content" readonly="readonly">${dto.content }</textarea>
			</div>
			<c:if test="${login.id ne null }">
			<a class="btn btn-primary" href="board4replyui.do?num=${dto.num }">답글</a>
			</c:if>
			<a class="btn btn-secondary" href="board4list.do?curPage=1&location=000&thema=000">목록</a>
 			<c:if test="${login.id ne null }">
				<a class="btn btn-warning" href="board4updateui.do?num=${dto.num }">수정</a>
				<a class="btn btn-danger" href="board4delete.do?num=${dto.num }">삭제</a>
			</c:if>
		</form>
	</div>

	<%@ include file="../com/footer.jsp"%>

</body>

</html>