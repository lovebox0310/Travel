<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>
<%@ include file="../com/head.jsp"%>
</head>
<body>
	<%@ include file="../com/top.jsp"%>
	<%@ include file="../com/navbar.jsp"%>
	<div class="container" style="margin-top: 30px">
		<div class="row">
			<div class="col-sm-2">
				<h3>지역별</h3>
				<p>지역별로 구분해드립니다.</p>
				<ul class="nav nav-pills flex-column">
					<li class="nav-item"><a class="nav-link" href="#">전체</a></li>
					<li class="nav-item"><a class="nav-link" href="#">서울</a></li>
					<li class="nav-item"><a class="nav-link" href="#">인천</a></li>
					<li class="nav-item"><a class="nav-link active" href="#">경기</a></li>
					<li class="nav-item"><a class="nav-link" href="#">부산</a></li>
					<li class="nav-item"><a class="nav-link" href="#">경주</a></li>
					<li class="nav-item"><a class="nav-link" href="#">경상</a></li>
					<li class="nav-item"><a class="nav-link" href="#">강원</a></li>
					<li class="nav-item"><a class="nav-link" href="#">전라</a></li>
					<li class="nav-item"><a class="nav-link" href="#">충청</a></li>
					<li class="nav-item"><a class="nav-link" href="#">제주</a></li>
				</ul>
				<h3>태마별</h3>
				<p>테마별로 구분해드립니다.</p>
				<ul class="nav nav-pills flex-column">
					<li class="nav-item"><a class="nav-link" href="#">전체</a></li>
					<li class="nav-item"><a class="nav-link" href="#">신규숙박지</a></li>
					<li class="nav-item"><a class="nav-link" href="#">부티크/모텔</a></li>
					<li class="nav-item"><a class="nav-link" href="#">게스트하우스</a></li>
					<li class="nav-item"><a class="nav-link" href="#">골프리조트&골프텔</a></li>
					<li class="nav-item"><a class="nav-link" href="#">공항 인근숙박</a></li>
					<li class="nav-item"><a class="nav-link" href="#">제주추천숙박</a></li>
					<li class="nav-item"><a class="nav-link active" href="#">일출/바다</a></li>
				</ul>
				<hr class="d-sm-none">
			</div>
			<div class="col-sm-10">
				<h2>숙박정보 게시판</h2>
				<p>
					여행 다니면서 추억이 되었던 장소는 많습니다. 특히 여행중에 피로를 씻어줄 편안한 숙박 시설은 빠질수가 없습니다. 이와
					같은 경험을 공유하고 싶은 분은 
					<c:if test="${login.id ne null }"><a href="board4insertui.do">글쓰기</a>를 누르세요.</c:if>
					<c:if test="${login.id eq null }"><a href="loginui.do">로그인</a>후 글쓰기가 가능합니다.</c:if>
				</p>
				<table class="table table-hover">
					<thead>
						<tr>
							<th>번호</th>
							<th>지역</th>
							<th>테마</th>
							<th>제목</th>
							<th>글쓴이</th>
							<th>날짜</th>
							<th>조회수</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${list }" var="dto">
							<tr>
								<td>${dto.num }</td>
								<td>${dto.location }</td>
								<td>${dto.thema }</td>
								<td><c:forEach begin="1" end="${dto.repIndent }">
									&nbsp;&nbsp;
									</c:forEach><a href="board4read.do?num=${dto.num }">${dto.title }</a></td>
								<td>${dto.writer }</td>
								<td>${dto.writeday }</td>
								<td>${dto.readcnt }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<ul class="pagination justify-content-center" style="margin: 20px 0">
		<li class="page-item ${to.curPage eq 1 ? 'disabled' : '' }"><a
			class="page-link"
			href="board4list.do?curPage=${to.curPage > 1 ? (to.curPage -1) : 1 }">Previous</a></li>
		<c:forEach begin="${to.beginPageNum }" end="${to.stopPageNum }"
			var="idx">
			<li class="page-item ${idx eq to.curPage ? 'active' : '' }"><a
				class="page-link" href="board4list.do?curPage=${idx }">${idx }</a></li>
		</c:forEach>
		<li class="page-item ${to.curPage eq to.totalPage ? 'disabled' : '' }">
			<a class="page-link"
			href="board4list.do?curPage=${to.curPage < to.totalPage ? (to.curPage +1) : to.totalPage }">Next</a>
		</li>
	</ul>
	<%@ include file="../com/footer.jsp"%>
</body>
</html>