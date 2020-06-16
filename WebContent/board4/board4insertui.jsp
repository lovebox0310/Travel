<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>글쓰기</title>
<%@ include file="../com/head.jsp"%>
<script type="text/javascript">
	$(document).ready(() => {
		$.ajax({
			url : "locationList",
			data : {},
			type : "GET",
			dataType : "json",
		}).done(function(result) {
			if (result[1]["msg"] != "success") {
				console.log("fail");
			} else {
				const o = result[2];
				const list = JSON.parse(o["list"]);
				for (let i = 0; i < list.length; i++) {
					const obj = list[i];
					const values = [];
					for ( const key in obj) {
						if (obj.hasOwnProperty(key)) {
							const element = obj[key];
							values.push(element)
						}
					}
					$("#location").append($("<option value='"+values[0]+"'>"+values[1]+"</option>"));
				}
			}
		});

		$.ajax({
			url : "themaList",
			data : {},
			type : "GET",
			dataType : "json",
		}).done(function(result) {
			if (result[1]["msg"] != "success") {
				console.log("fail");
			} else {
				const o = result[2];
				const list = JSON.parse(o["list"]);
				for (let i = 0; i < list.length; i++) {
					const obj = list[i];
					const values = [];
					for ( const key in obj) {
						if (obj.hasOwnProperty(key)) {
							const element = obj[key];
							values.push(element)
						}
					}
					$("#thema").append($("<option value='"+values[0]+"'>"+values[1]+"</option>"));
				}
			}
		});
	});
</script>
</head>

<body>
	<%@ include file="../com/top.jsp"%>
	<%@ include file="../com/navbar.jsp"%>

	<div class="container" style="margin: 30px auto 30px auto;">
		<h2>글쓰기</h2>
		<form action="board4insert.do">
			<div class="form-group">
				<label for="location">지역:</label> <select class="form-control" id="location" name="location"></select>
			</div>
			<div class="form-group">
				<label for="thema">태마:</label> <select class="form-control" id="thema" name="thema"></select>
			</div>
			<div class="form-group">
				<label for="writer">작성자:</label> <input type="text" class="form-control" id="writer" name="writer" value="${login.id }" readonly="readonly">
			</div>
			<div class="form-group">
				<label for="title">제목:</label> <input type="text" class="form-control" id="title" placeholder="제목" name="title">
			</div>
			<div class="form-group">
				<label for="exampleFormControlTextarea1">내용:</label>
				<textarea class="form-control" id="exampleFormControlTextarea1" rows="3" name="content"></textarea>
			</div>
			<button type="submit" class="btn btn-primary">글쓰기</button>
		</form>
	</div>

	<%@ include file="../com/footer.jsp"%>

</body>

</html>