<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="kr.co.dao.Board6DAO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fm"%>

<!DOCTYPE html>
<html>
<head>
<title>Q&amp;A</title>
<meta charset="utf-8">
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
		<h1 class="far fa-comments">&nbsp;Q&amp;A</h1>
		<p>
			<strong>회원 전용</strong> Q&amp;A 게시판입니다. 궁금하신 사항은 '<strong>질문하기</strong>'로 문의해주세요.
		</p>


		<div class="dropdown">
			<button type="button" class="fas fa-bars btn btn-info dropdown-toggle" data-toggle="dropdown">&nbsp;나의 Q&amp;A로 가기</button>
			<div class="dropdown-menu">
				<a class="dropdown-item" href="board6asklist.do?id=${param.id}">나의 질문 목록</a>
				<div class="dropdown-divider"></div>
				<a class="dropdown-item" href="board6replylist.do?id=${param.id}">나의 답변 목록</a>
			</div>
		</div>
		<br>

		<table class="table table-hover">

			<thead class="thead-dark">
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
							<c:if test="${dto.repIndent>0}">
								<svg class="bi bi-arrow-return-right" width="1em" height="1em" viewBox="0 0 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
  <path fill-rule="evenodd" d="M10.146 5.646a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-3 3a.5.5 0 0 1-.708-.708L12.793 9l-2.647-2.646a.5.5 0 0 1 0-.708z" />
  <path fill-rule="evenodd" d="M3 2.5a.5.5 0 0 0-.5.5v4A2.5 2.5 0 0 0 5 9.5h8.5a.5.5 0 0 0 0-1H5A1.5 1.5 0 0 1 3.5 7V3a.5.5 0 0 0-.5-.5z" />
</svg>	Re :
							</c:if>
							<a style="color: rgb(79, 79, 79);" href="board6read.do?num=${dto.num}&id=${param.id}">
								<strong>${dto.title}</strong> <strong style="color: rgb(240, 70, 74);">[${count}]</strong>
							</a>

						</td>

						<td>${dto.writer}</td>
						<td>${dto.id}</td>
						<td>${dto.writeday}</td>
						<td>${dto.readcnt}</td>
					</tr>
				</c:forEach>
			</tbody>

		</table>






		<ul class="pagination justify-content-center">
			<li class="page-item ${to.curPage eq 1 ? 'disabled' : '' }"><a class="page-link"
					href="board6qnalist.do?curPage=${to.curPage ne 1 ? (to.curPage - 1) : 1 }&id=${param.id}&search=${search}&find=${find}">Previous</a></li>
			<c:forEach begin="${to.beginPageNum }" end="${to.stopPageNum }" var="idx">
				<li class="page-item ${idx eq to.curPage ? 'active' : '' }"><a class="page-link"
						href="board6qnalist.do?curPage=${idx }&id=${param.id}&search=${search}&find=${find}">${idx }</a></li>
			</c:forEach>
			<li class="page-item ${to.curPage eq to.totalPage ? 'disabled' : '' }"><a class="page-link"
					href="board6qnalist.do?curPage=${to.curPage ne to.totalPage ? to.curPage + 1 : to.totalPage }&id=${param.id}&search=${search}&find=${find}">Next</a></li>
		</ul>



		<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
			<form class="form-inline " name='sform' method='get' action='./board6qnalist.do'>
				<input type="hidden" name="id" value="${param.id}">
				<aside>
					<select class="btn btn-info btn-sm" name='find'>
						<option value='writer'>글쓴이</option>
						<option value='title'>제목</option>
					</select> <input class="form-control mr-sm-2" type="text" name='search'>
					<button class="btn btn-success btn-sm" type='submit'>검색</button>
					<a class="btn btn-success btn-sm" href="board6qnalist.do?id=${param.id}">전체글 보기</a>
				</aside>

			</form>

		</nav>

		<br>
		<a style="position: relative; left: 90%" href="board6askui.do?id=${param.id}&writer=${writer}" class="btn btn-info">
			<strong>질문하기</strong>
		</a>





	</div>


	<%@ include file="../com/footer.jsp"%>
</body>
</html>