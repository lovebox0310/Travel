<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
    
    
<div class="float-right">
	<c:if test="${empty login }">
		<a href='Board5loginui.do'>로그인</a> &nbsp;|&nbsp;
			<a href="Board5signupui.do">회원가입</a>
			&nbsp;
	</c:if>
	<c:if test="${not empty login }">
		<a href="Board5profile.do?id=${login.id }" data-toggle="tooltip" data-placement="bottom" title="내 정보 보기">${login.id }님</a>

		<c:if test="${login.authority eq '00' }">
				&nbsp;|&nbsp;
				<a href="Board5memberlistui.do">회원관리</a>
		</c:if>
			
			&nbsp;|&nbsp;			
			<a href="Board5logout.do">로그아웃</a>
	</c:if>
	&nbsp;
</div>

<div class="input-group mb-3 justify-content-center">
	<div class="input-group-prepend">

		<a class="navbar-brand mx-auto d-block" href="Board5list.do"">
			<img src="Board5/logo.png" width="100%" alt="event">
		</a>

	</div>
</div>