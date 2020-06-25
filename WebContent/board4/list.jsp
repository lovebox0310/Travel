<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>
<%@ include file="../com/head.jsp"%>
<script>
	//지역별/테마별 버튼 클릭시 조회
	document.addEventListener("DOMContentLoaded", function() {
	})

	function onClickBtn() {
		const btn_list = document.getElementsByClassName('nav-link active');

		let lid = '000';
		let tid = '000';

		for (let i = 0; i < btn_list.length; i++) {
			const element = btn_list[i];
			if (element.attributes.lid != undefined) {
				lid = element.getAttribute('lid');
			}
			if (element.attributes.tid != undefined) {
				tid = element.getAttribute('tid');
			}
		}
		window.location.href = 'board4list.do?curPage=1&location=' + lid
				+ '&thema=' + tid;
	}

	function onOverBtn(taraget) {
		let btn_list = document.getElementsByClassName('nav-link active');
		for (let i = 0; i < btn_list.length; i++) {
			const element = btn_list[i];
			if (element.attributes.lid != undefined
					&& taraget.attributes.lid != undefined) {
				element.classList.remove('active');
				taraget.classList.add('active');
			}
			if (element.attributes.tid != undefined
					&& taraget.attributes.tid != undefined) {
				element.classList.remove('active');
				taraget.classList.add('active');
			}
		}
	}

	function onOutBtn(target, id) {
		let active_btn_list = target.getElementsByClassName('nav-link active');
		let btn_list = target.getElementsByTagName("a");

		for (let j = 0; j < active_btn_list.length; j++) {
			const element = active_btn_list[j];
			element.classList.remove('active');
		}

		for (let i = 0; i < btn_list.length; i++) {
			const element = btn_list[i];

			if (element.getAttribute('lid') == id) {
				console.log(element.getAttribute('lid'));
				element.classList.add('active');
			}

			if (element.getAttribute('tid') == id) {
				console.log(element.getAttribute('tid'));
				element.classList.add('active');
			}
		}

	}
	
</script>
</head>
<body>
	<%@ include file="../com/top.jsp"%>
	<%@ include file="../com/navbar.jsp"%>
	<div class="container" style="margin: 30px auto 30px auto;">
		<div class="row">
			<div class="col-sm-2">
				<h3>지역별</h3>
				<p>지역별로 구분해드립니다.</p>
				<ul class="nav nav-pills flex-column" onmouseout="onOutBtn(this, '${location }')">
					<c:forEach items="${locationList }" var="l_item">
						<li class="nav-item"><a class="nav-link ${l_item.lid eq location ? 'active' : '' }" lid="${l_item.lid }" href="#" onclick="onClickBtn()"
								onmouseover="onOverBtn(this)">${l_item.lname }</a></li>
					</c:forEach>

				</ul>
				<h3>태마별</h3>
				<p>테마별로 구분해드립니다.</p>
				<ul class="nav nav-pills flex-column" onmouseout="onOutBtn(this, '${thema }')">
					<c:forEach items="${themaList }" var="t_item">
						<li class="nav-item"><a class="nav-link ${t_item.tid eq thema ? 'active' : '' }" tid="${t_item.tid }" href="#" onclick="onClickBtn()"
								onmouseover="onOverBtn(this)">${t_item.tname }</a></li>
					</c:forEach>

				</ul>
				<hr class="d-sm-none">
			</div>
			<div class="col-sm-10">
				<h2>숙박정보 게시판</h2>
				<p>
					여행 다니면서 추억이 되었던 장소는 많습니다. 특히 여행중에 피로를 씻어줄 편안한 숙박 시설은 빠질수가 없습니다. 이와 같은 경험을 공유하고 싶은 분은
					<c:if test="${login.id ne null }">
						<a href="board4insertui.do">글쓰기</a>를 누르세요.</c:if>
					<c:if test="${login.id eq null }">
						<a href="loginui.do">로그인</a>후 글쓰기가 가능합니다.</c:if>
						리스트가 필요하신분은 <button type="button" onclick="exportToExcel()">엑셀다운로드</button>을 클릭 바랍니다.
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
								<td>
									<c:forEach begin="1" end="${dto.repIndent }" var="idx">
										<c:if test="${dto.repIndent > idx}">
											&nbsp;&nbsp;
										</c:if>
										<c:if test="${dto.repIndent eq idx}">
											&nbsp;<%@ include file="../com/icon/arrowReturnRight.jsp" %>
										</c:if>
									</c:forEach>
									<a href="board4read.do?num=${dto.num }">${dto.title }</a>
									<c:if test="${dto.filename ne null }">
										&nbsp;<span style="color: #666;"><%@ include file="../com/icon/cardImage.jsp" %></span>
									</c:if>
								</td>
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
		<li class="page-item ${to.curPage eq 1 ? 'disabled' : '' }"><a class="page-link"
				href="board4list.do?curPage=${to.curPage > 1 ? (to.curPage -1) : 1 }&location=${location }&thema=${thema }">Previous</a></li>
		<c:forEach begin="${to.beginPageNum }" end="${to.stopPageNum }" var="idx">
			<li class="page-item ${idx eq to.curPage ? 'active' : '' }"><a class="page-link"
					href="board4list.do?curPage=${idx }&location=${location }&thema=${thema }">${idx }</a></li>
		</c:forEach>
		<li class="page-item ${to.curPage eq to.totalPage ? 'disabled' : '' }"><a class="page-link"
				href="board4list.do?curPage=${to.curPage < to.totalPage ? (to.curPage +1) : to.totalPage }&location=${location }&thema=${thema }">Next</a></li>
	</ul>
	<%@ include file="../com/footer.jsp"%>
</body>
</html>