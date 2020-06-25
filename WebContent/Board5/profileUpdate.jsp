<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보 보기</title>
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

	<div class="container">
		<form action="Board5profileupdate.do">
			<div class="row">
				<div class="col-12">
					<div class="card">

						<div class="card-body">
							<div class="card-title mb-4">
								<div class="d-flex justify-content-start">
									<div class="image-container">
										<img src="http://placehold.it/150x150" id="imgProfile" style="width: 150px; height: 150px" class="img-thumbnail" />
										<div class="middle">
											<input type="button" class="btn btn-secondary" id="btnChangePicture" value="Change" /> <input type="file" style="display: none;"
												id="profilePicture" name="file" />
										</div>
									</div>
									<div class="userData ml-3">
										<h2 class="d-block" style="font-size: 1.5rem; font-weight: bold">
											<a href="javascript:void(0);">${memberDTO.id }</a>
										</h2>
										<h6 class="d-block">
											<a href="javascript:void(0)">1,500</a>
											Image Uploads
										</h6>
										<h6 class="d-block">
											<a href="javascript:void(0)">300</a>
											Board Posts
										</h6>
									</div>
									<div class="ml-auto">
										<input type="button" class="btn btn-primary d-none" id="btnDiscard" value="Discard Changes" />
									</div>
								</div>
							</div>

							<div class="row">
								<div class="col-12">
									<ul class="nav nav-tabs mb-4" id="myTab" role="tablist">
										<li class="nav-item"><a class="nav-link active" id="basicInfo-tab" data-toggle="tab" href="#basicInfo" role="tab"
												aria-controls="basicInfo" aria-selected="true">Basic Info</a></li>
										<li class="nav-item"><a class="nav-link" id="connectedServices-tab" data-toggle="tab" href="#connectedServices" role="tab"
												aria-controls="connectedServices" aria-selected="false">Connected Services</a></li>
									</ul>
									<div class="tab-content ml-1" id="myTabContent">
										<div class="tab-pane fade show active" id="basicInfo" role="tabpanel" aria-labelledby="basicInfo-tab">


											<div class="row">
												<div class="col-sm-3 col-md-2 col-5">
													<label style="font-weight: bold;">Full Name</label>
												</div>
												<div class="col-md-8 col-6">
													<input name="name" value="${memberDTO.name }">
												</div>
											</div>
											<hr />

											<div class="row">
												<div class="col-sm-3 col-md-2 col-5">
													<label style="font-weight: bold;">Birth Date</label>
												</div>
												<div class="col-md-8 col-6">March 22, 1994.</div>
											</div>
											<hr />


											<div class="row">
												<div class="col-sm-3 col-md-2 col-5">
													<label style="font-weight: bold;">Age</label>
												</div>
												<div class="col-md-8 col-6">
													<input type="number" name="age" value="${memberDTO.age}">
												</div>
											</div>
											<hr />
											<div class="row">
												<div class="col-sm-3 col-md-2 col-5">
													<label style="font-weight: bold;">Password</label>
												</div>
												<div class="col-md-8 col-6">
													<input type="password" name="pw" value="${memberDTO.pw }" required="required">
												</div>
											</div>
											<hr />
											<div class="row">
												<div class="col-sm-3 col-md-2 col-5">
													<label style="font-weight: bold;">Something</label>
												</div>
												<div class="col-md-8 col-6">Something</div>
											</div>
											<hr />

										</div>
										<div class="tab-pane fade" id="connectedServices" role="tabpanel" aria-labelledby="ConnectedServices-tab">Facebook, Google, Twitter
											Account that are connected to this account</div>
									</div>
								</div>
							</div>


						</div>

					</div>
				</div>
			</div>
			<br>
			<button onclick="alert('내정보를 수정하시겠습니까?');" class="btn btn-outline-primary" >완료</button>
			<a class="btn btn-outline-primary" href="Board5list.do">목록</a>
		</form>
	</div>
	<jsp:include page="footer.jsp" />

</body>
</html>