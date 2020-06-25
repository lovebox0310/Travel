<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fm"%>

<!DOCTYPE html>
<html>
<head>
<%@ include file="../com/head.jsp" %>
<title>Insert title here</title>
</head>

<style>
	.alignleft{
		float: left;
	}
	.alignright{
		float: right;
	}
</style>

<body>
<%@ include file="../com/top.jsp" %>
<%@ include file="../com/navbar.jsp" %>
<div class="jumbotron jumbotron-fluid">
<div class="container">

		<div style="clear: both; margin-bottom: 50px">
		<div class="alignleft">

			<c:choose>
				<c:when test="${empty login}">
				</c:when>
				
				<c:otherwise>
				${login.id}님 로그인 되었습니다.
				</c:otherwise>
			</c:choose>
		</div>
		<div class="alignright">
			<c:if test="${login.id == 'adm'}">	<!-- 'adm' = 관리자계정 -->
							
				<a href="board6notice_insertui.do">글쓰기</a>
		
			</c:if>			
		</div>			
	</div> 
		 
	<table border="1" class="table">
		<thead class="thead-dark">
			<tr align="center">
				
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>조회수</th>
			</tr>
		</thead>

		<tbody>
			<c:forEach items="${list}" var="no_dto">
				<tr align="center">
					
					<td width="650px">
						<a href="board6notice_read.do?num=${no_dto.num}">${no_dto.title}</a>
					</td>

					<td width="150px">${no_dto.writer}</td>
					<td width="200px">${no_dto.writeday}</td>
					<td width="150px">${no_dto.readcnt}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div align="center">
	<a href="board6notice_list.do?curPage=${(to.curPage-1) > 0 ? (to.curPage-1) : 1}">&laquo;</a>
	&nbsp;&nbsp;

	<c:forEach begin="${to.beginPageNum}" end="${to.stopPageNum}" var="idx">

		<c:if test="${to.curPage == idx}">
			<a style="font-size: 30px;" href="board6notice_list.do?curPage=${idx}">${idx}</a>&nbsp;&nbsp;
	</c:if>
		<c:if test="${to.curPage != idx}">
			<a href="board6notice_list.do?curPage=${idx}">${idx}</a>&nbsp;&nbsp;
	</c:if>

	</c:forEach>

	<a href="board6notice_list.do?curPage=${(to.curPage+1) < to.totalPage ? (to.curPage+1) : to.totalPage }">&raquo;</a>
	</div>


</div>
</div>
</body>
</html>