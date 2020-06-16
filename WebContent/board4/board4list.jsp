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
<script>
	//지역별/테마별 버튼 클릭시 조회
	document.addEventListener("DOMContentLoaded", function() {
	})
	
	function onClickBtn(href) {
		console.log("클릭됨!!" );
		console.log(document.getElementsByClassName('nav-link active'));
		const btn_list = document.getElementsByClassName('nav-link active');
		for (let i = 0; i < btn_list.length; i++) {
			const element = btn_list[i];
			let lid = '000';
			let tid = '000';
			if (element.attributes.lid != undefined) {
				lid = element.getAttribute('lid');
			}
			if (element.attributes.tid != undefined) {
				tid = element.getAttribute('tid');
			}
				console.log('lid', lid);
				console.log('tid', tid);
			const url = href + '?curPage=1&location='+lid+'&thema='+tid;
			// location.href = url;
				location.href =  url;
				//  location.replace(url);
		}
	}

	function onOverBtn(taraget) {
		let btn_list = document.getElementsByClassName('nav-link active');
		for (let i = 0; i < btn_list.length; i++) {
			const element = btn_list[i];
			if (element.attributes.lid != undefined && taraget.attributes.lid != undefined) {
				element.classList.remove('active');
				taraget.classList.add('active');
			}
			if (element.attributes.tid != undefined && taraget.attributes.tid != undefined) {
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
		}
		
	}
</script>
</head>
<body>
	<%@ include file="../com/top.jsp"%>
	<%@ include file="../com/navbar.jsp"%>
	<div class="container" style="margin-top: 30px">
		<div class="row">
			<div class="col-sm-2">
				<h3>지역별</h3>
				<p>지역별로 구분해드립니다.</p>
				<ul class="nav nav-pills flex-column" onmouseout="onOutBtn(this, '000')">
					<li class="nav-item"><a class="nav-link active" lid="000" href="#" onclick="onClickBtn()" onmouseover="onOverBtn(this)">전체</a></li>
					<li class="nav-item"><a class="nav-link" lid="001" href="board4list.do" onclick="onClickBtn(this.href)" onmouseover="onOverBtn(this)">서울</a></li>
					<li class="nav-item"><a class="nav-link" lid="002" href="board4list.do" onclick="onClickBtn(this.href)" onmouseover="onOverBtn(this)">인천</a></li>
					<li class="nav-item"><a class="nav-link" lid="003" href="board4list.do" onclick="onClickBtn(this.href)" onmouseover="onOverBtn(this)">경기</a></li>
					<li class="nav-item"><a class="nav-link" lid="004" href="board4list.do" onclick="onClickBtn(this.href)" onmouseover="onOverBtn(this)">부산</a></li>
					<li class="nav-item"><a class="nav-link" lid="005" href="board4list.do" onclick="onClickBtn(this.href)" onmouseover="onOverBtn(this)">경주</a></li>
					<li class="nav-item"><a class="nav-link" lid="006" href="board4list.do" onclick="onClickBtn(this.href)" onmouseover="onOverBtn(this)">경상</a></li>
					<li class="nav-item"><a class="nav-link" lid="007" href="board4list.do" onclick="onClickBtn(this.href)" onmouseover="onOverBtn(this)">강원</a></li>
					<li class="nav-item"><a class="nav-link" lid="008" href="board4list.do" onclick="onClickBtn(this.href)" onmouseover="onOverBtn(this)">전라</a></li>
					<li class="nav-item"><a class="nav-link" lid="009" href="board4list.do" onclick="onClickBtn(this.href)" onmouseover="onOverBtn(this)">충청</a></li>
					<li class="nav-item"><a class="nav-link" lid="010" href="board4list.do" onclick="onClickBtn(this.href)" onmouseover="onOverBtn(this)">제주</a></li>
				</ul>
				<h3>태마별</h3>
				<p>테마별로 구분해드립니다.</p>
				<ul class="nav nav-pills flex-column" onmouseout="onOutBtn(this, '000')">
					<li class="nav-item"><a class="nav-link active" tid="000" href="#" onclick="onClickBtn()" onmouseover="onOverBtn(this)">전체</a></li>
					<li class="nav-item"><a class="nav-link" tid="001" href="#" onclick="onClickBtn()" onmouseover="onOverBtn(this)">신규숙박지</a></li>
					<li class="nav-item"><a class="nav-link" tid="002" href="#" onclick="onClickBtn()" onmouseover="onOverBtn(this)">부티크/모텔</a></li>
					<li class="nav-item"><a class="nav-link" tid="003" href="#" onclick="onClickBtn()" onmouseover="onOverBtn(this)">게스트하우스</a></li>
					<li class="nav-item"><a class="nav-link" tid="004" href="#" onclick="onClickBtn()" onmouseover="onOverBtn(this)">골프리조트&골프텔</a></li>
					<li class="nav-item"><a class="nav-link" tid="005" href="#" onclick="onClickBtn()" onmouseover="onOverBtn(this)">공항 인근숙박</a></li>
					<li class="nav-item"><a class="nav-link" tid="006" href="#" onclick="onClickBtn()" onmouseover="onOverBtn(this)">제주추천숙박</a></li>
					<li class="nav-item"><a class="nav-link" tid="007" href="#" onclick="onClickBtn()" onmouseover="onOverBtn(this)">일출/바다</a></li>
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