<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fm"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Q&amp;A</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
</head>
<body>
	<%@ include file="../com/top.jsp"%>
	<%@ include file="../com/navbar.jsp"%>

	<div class="container" style="margin: 30px auto 30px auto;">




		<h1>Question</h1>

		<form action="board6ask.do" method="post" class="was-validated" enctype="multipart/form-data">
			<div class="form-group">
				<label for="id">ID : </label> <input name="id" class="form-control" readonly value="${param.id}">
			</div>
			<div class="form-group">
				<label for="writer">작성자 : </label> <input class="form-control" name="writer" readonly value="${writer}">
			</div>
			<div class="form-group">
				<label for="title">제목 : </label> <input type="text" class="form-control" placeholder="제목을 입력하세요." name="title" required>
				<div class="valid-feedback">입력 완료</div>
				<div class="invalid-feedback">정보를 입력해주세요.</div>
			</div>
			내용 : <br>
			<textarea style="width: 100%" placeholder="내용을 입력하세요." name="content" required></textarea>
			<div class="valid-feedback">입력 완료</div>
			<div class="invalid-feedback">정보를 입력해주세요.</div>
			<br>
			<p>파일:</p>
			<div class="custom-file mb-3">
				<input type="file" class="custom-file-input" id="customFile" name="filename">
				<label class="custom-file-label" for="customFile">Choose file</label>
			</div>
			<br><br>

			<button type="submit" class="btn btn-info">작성완료</button>
			<a class="btn btn-secondary" href="board6qnalist.do?id=${param.id}">뒤로가기</a>
		</form>



	</div>
	
<script>
$(".custom-file-input").on("change", function() {
  var fileName = $(this).val().split("\\").pop();
  $(this).siblings(".custom-file-label").addClass("selected").html(fileName);
});
</script>
</body>
</html>