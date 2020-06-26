<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원관리</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<link rel="shortcut icon" href="image/favicon.ico">

<style type="text/css">
body {
	padding-bottom: 50px;
}

.image-container {
	position: relative;
}

.image {
	opacity: 1;
	display: block;
	width: 100%;
	height: auto;
	transition: .5s ease;
	backface-visibility: hidden;
}

.middle {
	transition: .5s ease;
	opacity: 0;
	position: absolute;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
	-ms-transform: translate(-50%, -50%);
	text-align: center;
}

.image-container:hover .image {
	opacity: 0.3;
}

.image-container:hover .middle {
	opacity: 1;
}
</style>

</head>
<body>

	<jsp:include page="/Board5/head.jsp" />

	<jsp:include page="/com/navbar.jsp" />
	<%-- 	<div class="container">
		<h2>회원목록</h2>
		<br>
		<table class="table">
			<thead>
				<tr class="text-center">
					<th>ID</th>
					<th>PW</th>
					<th>NAME</th>
					<th>AGE</th>
					<th>AUTHORITY</th> ${login.authority eq '00' ? '<th>GRANT</th>' : ''}
				</tr>
			</thead>

			<tbody>
				<c:forEach items="${list }" var="dto">
					<tr class="text-center">
						<td>${dto.id }</td>
						<td>${dto.pw }</td>
						<td>${dto.name }</td>
						<td>${dto.age }</td>

						<c:choose>
							<c:when test="${dto.authority eq '00'}">
								<td>admin</td>
								<td>
									<a href="revoke.do?id=${dto.id }" class="btn btn-outline-primary btn-sm">revoke</a>
								</td>
							</c:when>
							<c:when test="${dto.authority eq '01'}">
								<td>member</td>
								<td>
									<a href="grant.do?id=${dto.id }" class="btn btn-outline-primary btn-sm">grant</a>
								</td>
							</c:when>
							<c:otherwise>
								<td>not member</td>
								<td>
									<a href="#" class="btn btn-outline-primary btn-sm disabled">lock</a>
								</td>
							</c:otherwise>
						</c:choose>
					</tr>
				</c:forEach>

			</tbody>
		</table>
	</div> --%>


	<section class="container mt-4 mb-4">
		<div class="container">
			<h2>회원목록</h2><br>
			<div class="row mb-3">
				<c:forEach items="${list }" var="dto">
					<div class="col-md-6">
						<div class="d-flex flex-row border rounded">
							<div class="p-0 w-30">
								<img src="http://placehold.it/150x150" id="imgProfile" style="width: 150px; height: 150px" class="img-thumbnail" />

							</div>
							<div class="pl-3 pt-2 pr-2 pb-2 w-70 border-left">
								<h4 class="text-primary">${dto.id }</h4>
								<h5 class="text-info">${dto.name }(${dto.age })</h5>

								<c:choose>
									<c:when test="${dto.authority eq '00'}">
										<p>admin</p>

										<a href="Board5revoke.do?id=${dto.id }" class="btn btn-outline-primary btn-sm">revoke</a>

									</c:when>
									<c:when test="${dto.authority eq '01'}">
										<p>member</p>

										<a href="Board5grant.do?id=${dto.id }" class="btn btn-outline-primary btn-sm">grant</a>

									</c:when>
									<c:otherwise>
										<p>not member</p>

										<a href="#" class="btn btn-outline-primary btn-sm disabled">lock</a>

									</c:otherwise>
								</c:choose>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>

		</div>
	</section>


	<jsp:include page="footer.jsp" />

</body>
</html>