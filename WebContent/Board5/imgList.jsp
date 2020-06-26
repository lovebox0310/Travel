<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>맛집 페이지</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>

<link rel="shortcut icon" href="image/favicon.ico">

</head>
<body>

	<jsp:include page="/Board5/head.jsp" />

	<jsp:include page="/com/navbar.jsp" />

	<div class="container">
		<ul class="nav nav-tabs">
			<li class="nav-item"><a class="nav-link ${to.nowLocationID eq null ? 'active' : to.nowLocationID eq '' ? 'active' : '' }" href="Board5imglist.do">전체</a></li>
			<c:forEach items="${locations }" var="location">
				<c:if test="${location.lid eq to.nowLocationID }">
					<li class="nav-item"><a class="nav-link active" href="Board5imglist.do?locationID=${location.lid }">${location.lname }</a></li>
				</c:if>
				<c:if test="${location.lid ne to.nowLocationID }">
					<li class="nav-item"><a class="nav-link" href="Board5imglist.do?locationID=${location.lid }">${location.lname}</a></li>
				</c:if>
			</c:forEach>
		</ul>

		<table class="table table-borderless">
			<c:forEach items="${list }" var="dto" varStatus="status">
				<c:if test="${status.index % 3 eq 0 }">
					<br>
					<tr>
				</c:if>
				<td class="text-center">
					<a href="Board5read.do?num=${dto.num }">
						<img width="200px" height="200px" alt="${dto.fileDTO.filePath }" src="img/${dto.fileDTO.fileName }" class="rounded" /> <br>
					</a>
					<a href="Board5read.do?num=${dto.num }">
						<strong>${dto.title }</strong> <br>
					</a>
					<small>${dto.locationName }&nbsp; &nbsp;${dto.writer }</small> <br> <small>${fn:substring(dto.writeday, 0, 10)}&nbsp; <small>조회</small>
						${dto.readcnt }
					</small>
				</td>
				<c:if test="${status.index % 3 eq 2}">
					</tr>
				</c:if>
			</c:forEach>
		</table>

		<br>

		<c:if test="${!empty login }">
			<a href="Board5insertui.do" class="btn btn-outline-primary">글쓰기</a>
		</c:if>

		<a href="Board5list.do" class="btn btn-outline-primary float-right">
			<i class="far fa-list-alt"></i>
		</a>

		<form action="Board5imglist.do" method="get">
			<ul class="pagination justify-content-center">
				<li class="page-item"><a class="page-link" ${to.curPage eq 1 ? 'style="visibility:hidden; pointer-events: none; color: #cccc;"' : '' }
						href="Board5imglist.do?searchTitle=${searchTitle }&locationID=${to.nowLocationID }&curPage=${to.curPage eq 1 ? 1 : to.curPage - 1 }">&laquo;</a></li>
				<c:forEach begin="${to.beginPageNum }" end="${to.stopPageNum }" var="idx">
					<c:if test="${to.curPage == idx }">
						<li class="page-item active"><a class="page-link"
								href="Board5imglist.do?searchTitle=${searchTitle }&locationID=${to.nowLocationID }&curPage=${idx }">${idx }</a></li>
					</c:if>
					<c:if test="${to.curPage != idx }">
						<li class="page-item"><a class="page-link" href="Board5imglist.do?searchTitle=${searchTitle }&locationID=${to.nowLocationID }&curPage=${idx }">${idx }</a></li>
					</c:if>
				</c:forEach>
				<li class="page-item"><a class="page-link"
						${to.curPage eq to.totalPage ? 'style="visibility:hidden; pointer-events: none; color: #cccc;"' : '' }
						href="Board5imglist.do?searchTitle=${searchTitle }&locationID=${to.nowLocationID }&curPage=${to.curPage eq to.totalPage ? to.totalPage : to.curPage + 1}">&raquo;</a>
				</li>
			</ul>

			<jsp:include page="search.jsp" />
		</form>
		<br>
		<div class="input-group mb-3 justify-content-center">
			<div class="input-group-prepend">
				<a class="navbar-brand" href="#">
					<img src="logo.gif" height="250px" alt="event">
				</a>
				<a class="navbar-brand" href="#">
					<img src="logo.gif" height="130px" alt="event">
				</a>
				<a class="navbar-brand" href="#">
					<img src="logo.gif" height="70px" alt="event">
				</a>
			</div>
		</div>
		<br>
	</div>

	<jsp:include page="footer.jsp" />
</body>
</html>