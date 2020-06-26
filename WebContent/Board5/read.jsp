<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<link rel="shortcut icon" href="image/favicon.ico">

</head>
<body>
	
	<jsp:include page="/Board5/head.jsp" />

	<jsp:include page="/com/navbar.jsp" />

	<form class="container" action="Board5updateui.do" method="post">
		<h2>게시글</h2>
		<input name="num" value="${boardDTO.num }" hidden="true"> <label for="exampleFormControlInput1">Title</label> <br>
		<div class="form-inline">
			<select class="custom-select" name="location" id="location" disabled="disabled">
				<optgroup label="지역">
					<option selected="selected" value="${boardDTO.locationID }">${boardDTO.locationName }</option>
				</optgroup>
			</select> &nbsp; <input name="title" class="form-control" id="exampleFormControlInput1" value="${boardDTO.title }" readonly="readonly">
		</div>

		<label for="exampleFormControlInput1">작성자: ${boardDTO.writer } | 조회수: ${boardDTO.readcnt} </label> <br>
		<a href="download?fileName=${fileDTO.fileName }">${fileDTO.orgFileName } &nbsp; 파일 다운로드</a>
		<img width="100%" alt="${fileDTO.filePath }" src="img/${fileDTO.fileName }" class="rounded">

		<div class="form-group">
			<label for="exampleFormControlTextarea1">textarea</label>
			<textarea class="form-control" id="exampleFormControlTextarea1" rows="5" name="content" readonly="readonly">${boardDTO.content }</textarea>
		</div>

		<c:if test="${login.id eq boardDTO.writer}">
			<button class="btn btn-outline-primary">수정</button>
			<a onclick="alert('이 글을 삭제하시겠습니까\n${boardDTO.title}');" class="btn btn-outline-primary" href="Board5delete.do?num=${boardDTO.num }">삭제</a>
		</c:if>
		<c:if test="${!empty login }">
			<a class="btn btn-outline-primary" href="Board5replyui.do?num=${boardDTO.num }&title=${boardDTO.title}&locationID=${boardDTO.locationID}">답글</a>
		</c:if>
		<a class="btn btn-outline-primary" href="#" onclick="historyBack()">이전</a>
		<a class="btn btn-outline-primary" href="Board5list.do">목록</a>
		<br><br>
		<div id="disqus_thread"></div>
		<script>
			(function() { // DON'T EDIT BELOW THIS LINE
				var d = document, s = d.createElement('script');
				s.src = 'https://travel5.disqus.com/embed.js';
				s.setAttribute('data-timestamp', +new Date());
				(d.head || d.body).appendChild(s);
			})();
		</script>

	</form>

	<script>
		function historyBack() {
			window.history.back();
		}
	</script>

</body>
</html>