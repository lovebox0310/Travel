<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="kr.co.dao.Board6DAO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fm"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>질문 목록</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>

</head>
<body>

	<%@ include file="../com/top.jsp"%>
	<%@ include file="../com/navbar.jsp"%>
	<div class="container" style="margin: 30px auto 30px auto;">
		<h1 class="fas fa-lightbulb">&nbsp;Questions</h1>
		<h5 >${writer} 회원님께서 질문하신 게시글 목록입니다.</h5><br>

		<div class="dropdown">
			<button type="button" class="fas fa-bars btn btn-secondary dropdown-toggle" data-toggle="dropdown">&nbsp;${writer}님의 질문 목록</button>
			<div class="dropdown-menu">
				<a class="dropdown-item" href="board6replylist.do?id=${param.id}">${writer}님의 답변 목록</a>
			</div>
		</div>
		<br>



		<table class="table table-hover">

			<thead class="thead-light">
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>글쓴이</th>
					<th>ID</th>
					<th>작성일</th>
					<th>조회수</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach items="${list}" var="dto">
					<tr>
						<td>${dto.num}</td>
						<c:set var="num" value="${dto.num}" />
						<%
							int number = (Integer) pageContext.getAttribute("num");
								Board6DAO dao = new Board6DAO();
								int count = dao.countComments(number);
								request.setAttribute("count", count);
						%>
						<td width="300px">
							<c:forEach begin="1" end="${dto.repIndent}">
								
&nbsp;&nbsp;
</c:forEach>
							<c:choose>
								<c:when test="${dto.id eq param.id}">
									<a href="board6read.do?num=${dto.num}&id=${param.id}">
									<strong style = "background-color:rgb(87,87,87); color : white;">&nbsp;${dto.title}&nbsp;</strong>
									<strong style="color: rgb(240, 70, 74);">[${count}]</strong></a>
								</c:when>
								<c:otherwise>
									<a style="color: rgb(79,79,79);" href="board6read.do?num=${dto.num}&id=${param.id}"><strong>${dto.title}</strong>
									<strong style="color: rgb(240, 70, 74);">[${count}]</strong></a>
								</c:otherwise>
							</c:choose>
						</td>
						<td>${dto.writer}</td>
						<td>${dto.id}</td>
						<td>${dto.writeday}</td>
						<td>${dto.readcnt}</td>
					</tr>
				</c:forEach>
			</tbody>

		</table>

		<br> <br>
		<a style="position: relative; left: 90%" class="btn btn-secondary" href="board6qnalist.do?id=${param.id}">목록</a>



	</div>




</body>
</html>