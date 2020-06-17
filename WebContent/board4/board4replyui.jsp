<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>답글 쓰기</title>
<%@ include file="../com/head.jsp"%>
<script>
	function historyBack() {
		window.history.back();
	}
	$(document).ready(function() {
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
							const value = obj[key];
							values.push(value);
							// console.log(value);
						}
					}
					if(values[0] == '${dto.location }') {
						$("#lname").val(values[1]);
					}
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
							const value = obj[key];
							values.push(value);
							// console.log(value);
						}
					}
					if(values[0] == '${dto.thema }') {
						$("#tname").val(values[1]);
					}
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
		<h2>수정</h2>
		<form action="board4reply.do">
			<input type="hidden" class="form-control" id="num" name="num" value="${dto.num }">
			<input type="hidden" class="form-control" id="location" name="location" value="${dto.location }">
			<input type="hidden" class="form-control" id="thema" name="thema" value="${dto.thema }">
			<div class="form-group">
				<label for="location">지역:</label> <input class="form-control" id="lname" name="lname" readonly="readonly">
			</div>
			<div class="form-group">
				<label for="thema">태마:</label> <input class="form-control" id="tname" name="tname" readonly="readonly">
			</div>
			<div class="form-group">
				<label for="writer">작성자:</label> <input type="text" class="form-control" id="writer" name="writer" value="${login.id }" readonly="readonly">
			</div>
			<div class="form-group">
				<label for="title">제목:</label> <input type="text" class="form-control" id="title" placeholder="제목" name="title" >
			</div>
			<div class="form-group">
				<label for="exampleFormControlTextarea1">내용:</label>
				<textarea class="form-control" id="exampleFormControlTextarea1" name="content" rows="3"></textarea>
			</div>
			<button type="submit" class="btn btn-primary">등록</button>
			<a class="btn btn-secondary" href="#" onclick="historyBack()">이전</a>
			<a class="btn btn-secondary" href="board4list.do?curPage=1&location=000&thema=000">목록</a>
		</form>
	</div>

	<%@ include file="../com/footer.jsp"%>
</body>
</html>