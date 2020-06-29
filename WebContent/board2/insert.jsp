<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>

</head>
<body>
	<%@ include file="../com/top.jsp"%>
	<%@ include file="../com/navbar.jsp"%>

	<div class="container" style="margin-bottom: 30px, auto">
		<form enctype="multipart/form-data" class="container" action="board2insert.do" method="post">
			<h2 class="text-center">글 쓰기</h2>
			<input name="num" value="${dto.num}" hidden="true"> <label for="exampleFormControlInput1">제목</label><br>
			<div class="form-inline">
				<select class="custom-select" name="location" id="location">
					<option value="001">서울</option>
					<option value="002">부산</option>
					<option value="003">대구</option>
					<option value="004">인천</option>
					<option value="005">광주</option>
					<option value="006">대전</option>
					<option value="007">울산</option>
					<option value="008">세종</option>
					<option value="009">경기</option>
					<option value="010">강원</option>
					<option value="011">충북</option>
					<option value="012">충남</option>
					<option value="013">전북</option>
					<option value="014">전남</option>
					<option value="015">경북</option>
					<option value="016">경남</option>
					<option value="017">제주</option>
					<option value="018">기타</option>
					
					
				</select> &nbsp; 
				<input placeholder="제목을 입력하세요" name="title" class="form-control" id="exampleFormControlInput1" value="${dto.title}" required="required">
			</div>
			<br> 
			<label for="exampleFormControlInput1">작성자</label> 
			<input name="writer" class="form-control" id="exampleFormControlInput1" value="${login.id}" readonly>
			<br>	
	  		<input type="file" name="file" class="form-control" id="exampleFormFile">

			<div class="form-group">
				<br> 
				<label for="exampleFormControlTextarea1">내용</label>
				<textarea placeholder="내용을 작성하세요" class="form-control" id="exampleFormControlTextarea1" rows="5" name="content" required>${dto.content}</textarea>
			</div>
			<button class="btn btn-primary" type="submit">등록</button>

		</form>

		<script>
			$(document).ready(function() {
				$("#location").val("${dto.location }");
			});
		</script>
	</div>
</body>
</html>
