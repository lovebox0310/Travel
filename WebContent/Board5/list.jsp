<%@page import="java.util.Date"%>
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

<%
	Date date = new Date();
	String year = date.getYear() + 1900 + "";
	int iMonth =  date.getMonth() + 1;
	int iDate = date.getDate();
	String realMonth = "";
	String realDate = "";
	if(iMonth <10){
		realMonth += "0" + iMonth;
	}else{
		realDate += iMonth;
	}
	if(iDate <10){
		realDate += "0" + iDate;
	}else{
		realDate += iDate;
	}
	out.print("&nbsp;오늘의 시간 " + year + "-" + realMonth + "-" + realDate);
	
	request.setAttribute("realDate", year + "-" + realMonth + "-" + realDate);

%>
	<jsp:include page="/Board5/head.jsp" />

	<jsp:include page="/com/navbar.jsp" />
	
	<br>
	
	<div class="container">
		<ul class="nav nav-tabs">
			<li class="nav-item"><a class="nav-link ${nowLocationID eq null ? 'active' : nowLocationID eq '' ? 'active' : '' }" href="Board5list.do">전체</a></li>
			<c:forEach items="${locations }" var="location">
				<c:if test="${location.lid eq nowLocationID }">
					<li class="nav-item"><a class="nav-link active" href="Board5list.do?locationID=${location.lid }">${location.lname }</a></li>
				</c:if>
				<c:if test="${location.lid ne nowLocationID }">
					<li class="nav-item"><a class="nav-link" href="Board5list.do?locationID=${location.lid }">${location.lname}</a></li>
				</c:if>
			</c:forEach>
		</ul>

		<table class="table">
			<thead>
				<tr class="text-center">
					<th>번호</th>
					<th>지역</th>
					<th style="width: 30%">제목</th>
					<th>글쓴이</th>
					<th>날짜</th>
					<th>조회수</th>
					${login.authority eq '00' ? '<th>삭제</th>' : '' }
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list }" var="dto">
					<tr class="text-center">
						<td>${dto.num }</td>
						<td>${dto.locationName }</td>
						<td class="text-left">
							<c:forEach begin="1" end="${dto.repIndent }">
		                  	&nbsp;&nbsp;
		                  	</c:forEach>
		                  	<c:if test="${dto.repIndent > 0}">
		                  	<svg class="bi bi-arrow-return-right" width="1em" height="1em" viewBox="0 0 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
  								<path fill-rule="evenodd" d="M10.146 5.646a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-3 3a.5.5 0 0 1-.708-.708L12.793 9l-2.647-2.646a.5.5 0 0 1 0-.708z"/>
  								<path fill-rule="evenodd" d="M3 2.5a.5.5 0 0 0-.5.5v4A2.5 2.5 0 0 0 5 9.5h8.5a.5.5 0 0 0 0-1H5A1.5 1.5 0 0 1 3.5 7V3a.5.5 0 0 0-.5-.5z"/>
							</svg>
							</c:if>
							<a href="Board5read.do?num=${dto.num }">${dto.title }</a>
							<c:if test="${dto.fileNum > 0}">
								<span class="text-secondary"><i class="far fa-file-image"></i></span>
							</c:if>
						</td>
						
						<td>${dto.writer }</td>
						
						<c:if test="${fn:substring(dto.writeday, 0, 10) eq realDate}">
						<td>${fn:substring(dto.writeday, 10, 16)}</td>
						</c:if>
						
						<c:if test="${fn:substring(dto.writeday, 0, 10) ne realDate}">
						<td>${fn:substring(dto.writeday, 0, 10)}</td>
						</c:if>
						
						<td>${dto.readcnt }</td>
						
						<c:if test="${login.authority eq '00'}">
							<td><a onclick="alert('이 글을 삭제하시겠습니까\n${dto.title}');" href="Board5delete.do?num=${dto.num }" class="btn btn-outline-primary btn-sm">삭제</a></td>
						</c:if>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<br>

		<c:if test="${empty login }">
			<a style="visibility: hidden;" href="Board5insertui.do" class="btn btn-outline-primary">글쓰기</a>
		</c:if>
		<c:if test="${!empty login }">
			<a href="Board5insertui.do" class="btn btn-outline-primary">글쓰기</a>
		</c:if>

		<a href="Board5imglist.do" class="btn btn-outline-primary float-right">
			<i class="far fa-images"></i>
		</a>

		<form action="Board5list.do" method="get">
			<ul class="pagination justify-content-center">
				<li class="page-item"><a class="page-link" ${to.curPage eq 1 ? 'style="visibility:hidden; pointer-events: none; color: #cccc;"' : '' }
						href="Board5list.do?searchTitle=${searchTitle }&location=${nowLocationID }&curPage=${to.curPage eq 1 ? 1 : to.curPage - 1 }">&laquo;</a></li>
				<c:forEach begin="${to.beginPageNum }" end="${to.stopPageNum }" var="idx">
					<c:if test="${to.curPage == idx }">
						<li class="page-item active"><a class="page-link" href="Board5list.do?searchTitle=${searchTitle }&location=${nowLocationID }&curPage=${idx }">${idx }</a></li>
					</c:if>
					<c:if test="${to.curPage != idx }">
						<li class="page-item"><a class="page-link" href="Board5list.do?searchTitle=${searchTitle }&location=${nowLocationID }&curPage=${idx }">${idx }</a></li>
					</c:if>
				</c:forEach>
				<li class="page-item"><a class="page-link"
						${to.curPage eq to.totalPage ? 'style="visibility:hidden; pointer-events: none; color: #cccc;"' : '' }
						href="Board5list.do?searchTitle=${searchTitle }&location=${nowLocationID }&curPage=${to.curPage eq to.totalPage ? to.totalPage : to.curPage + 1}">&raquo;</a>
				</li>
			</ul>

			<jsp:include page="search.jsp" />
		</form>

		<div class="input-group mb-3 justify-content-center">
			<div class="input-group-prepend">
				<a class="navbar-brand" href="#">
					<img src="Board5/logo.gif" height="250px" alt="event">
				</a>
				<a class="navbar-brand" href="#">
					<img src="Board5/logo.gif" height="130px" alt="event">
				</a>
				<a class="navbar-brand" href="#">
					<img src="Board5/logo.gif" height="70px" alt="event">
				</a>
			</div>
		</div>
		<br>
	</div>

	<jsp:include page="footer.jsp" />
	
</body>
</html>