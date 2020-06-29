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

</head>
<body>
	<%@ include file="../com/top.jsp"%>
	<%@ include file="../com/navbar.jsp"%>
	<div class="container" style="margin-bottom: 30px, auto">

		<form enctype="multipart/form-data" class="container" action="board2reply.do" method="post">
			<input name="num" value="${dto.num}" hidden="true">
			<h2 class="text-center">답글</h2>


			<label for="exampleFormControlInput1">제목</label><br>
			<div class="form-inline">

				<select class="custom-select" name="location" id="location">
					<c:forEach items="${siteList}" var="sito">
						<c:if test="${dto.location eq sito.sid}">
							<option value="${sito.sid}" selected>${sito.location}</option>
						</c:if>
					</c:forEach>
				</select> &nbsp; 
				<input placeholder="제목을 입력하세요" name="title" class="form-control" id="exampleFormControlInput1" required="required">
		      </div>
			  <br>
		      <label for="exampleFormControlInput1">작성자</label>
		      <input name="writer" class="form-control" id="exampleFormControlInput1" value="${login.id}" required="required" readonly>
			  <br>
			  <input type="file" name="file" class="form-control" id="exampleFormFile">	
			  
		      <div class="form-group">
		      	 <br>
		         <label for="exampleFormControlTextarea1">내용</label>
		         <textarea placeholder="내용을 작성하세요" class="form-control" id="exampleFormControlTextarea1" rows="5" name="content"></textarea>
		      </div>
		      <button class="btn btn-primary" type="submit">답글</button>
		</form>

		<script>
			$(document).ready(function() {
				$("#location").val("${dto.location }");
			});
		</script>
	</div>
</body>
</html>