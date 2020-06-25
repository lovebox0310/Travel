<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="contextPath" value="<%=request.getContextPath()%>"></c:set>

<nav class="navbar navbar-expand-sm bg-dark navbar-dark sticky-top" >
	<a class="navbar-brand" href="${contextPath }/main.jsp">Main</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
		<span class="navbar-toggler-icon"></span>
	</button>
	<div class="collapse navbar-collapse" id="collapsibleNavbar">
		<ul class="navbar-nav">
			<c:if test="${null ne login.id }">
				<li class="nav-item"><a class="nav-link" href="logout.do?id=${login.id }">로그아웃</a></li>
				<li class="nav-item"><a class="nav-link" href="memberinfo.do?id=${login.id }">회원정보</a></li>
			</c:if>
			<c:if test="${null eq login.id }">
				<li class="nav-item"><a class="nav-link" href="loginui.do">로그인</a></li>
				<li class="nav-item"><a class="nav-link" href="insertui.do">회원가입</a></li>
			</c:if>
			<li class="nav-item"><a class="nav-link" href="board2list.do?curPage=1&sid=">추천 여행지</a></li>
			<li class="nav-item"><a class="nav-link" href="http://172.30.1.50:8089/Travel3/list.do?curPage=1&locationCode=0">여행 메이트</a></li>
			<li class="nav-item"><a class="nav-link" href="board4list.do?curPage=1&location=000&thema=000">숙박 정보</a></li>
			<li class="nav-item"><a class="nav-link" href="Board5list.do">맛집 정보</a></li>
			<li class="nav-item"><a class="nav-link" href="board6notice_list.do?curPage=1">공지 사항</a></li>
<<<<<<< HEAD
			<li class="nav-item"><a class="nav-link" href="${empty login.id ? 'loginui.do' : 'board6qnalist.do?id=' }${login.id }">Q&amp;A</a></li>
=======
			<li class="nav-item"><a class="nav-link" href="http://172.30.1.59:8089/Travel6/main.jsp">Q&amp;A</a></li>
>>>>>>> d14bbed0f9a2594819b39db166b670cee18b6d4f
		</ul>
	</div>
</nav>