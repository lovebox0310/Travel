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
	<form enctype="multipart/form-data" class="container" action="Board5reply.do" method="post">
		<h2>답글 달기</h2>
		<input name="num" value="${dto.num }" hidden="true"> <input name="location" value="${dto.locationID }" hidden="true">
		<div class="form-group">
			<input name="writer" value="${login.id }" hidden="true">
		</div>

		<div class="form-group">
			<label for="exampleFormControlInput1">Title</label> <input name="title" value="${dto.title }" class="form-control" id="exampleFormControlInput1"
				required="required">
		</div>

		<div class="custom-file">
			<input type="file" class="custom-file-input" id="customFile" name="file"> <label class="custom-file-label" for="customFile">Choose
				file</label>
		</div>
		<img id="imgBox" width="50%" class="rounded" alt="" src="" />

		<div class="form-group">
			<label for="exampleFormControlTextarea1">textarea</label>
			<textarea class="form-control" id="exampleFormControlTextarea1" rows="5" name="content"></textarea>
		</div>
		<button class="btn btn-outline-primary">등록</button>
		<a class="btn btn-outline-primary" href="#" onclick="historyBack()">이전</a>
		<a class="btn btn-outline-primary" href="Board5list.do">목록</a>
		<br>
	</form>
	<jsp:include page="footer.jsp" />
	<script>
		$(".custom-file-input").on(
				"change",
				function() {
					var fileName = $(this).val().split("\\").pop();
					$(this).siblings(".custom-file-label").addClass("selected")
							.html(fileName);
				});

		$(function() {
			$("#customFile").on('change', function() {
				readURL(this);
			});
		});

		function readURL(input) {
			if (input.files && input.files[0]) {
				var reader = new FileReader();

				reader.onload = function(e) {

					$('#imgBox').attr('src', e.target.result);
				}

				reader.readAsDataURL(input.files[0]);
			}
		}

		function historyBack() {
			window.history.back();
		}
	</script>
</body>
</html>