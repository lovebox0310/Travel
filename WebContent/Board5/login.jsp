<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"
	integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>

<link rel="shortcut icon" href="image/favicon.ico">

</head>
<body>

	<jsp:include page="/Board5/head.jsp" />

	<jsp:include page="/com/navbar.jsp" />
	<br>

	<div class="container">
		<div class="row">
			<div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
				<div class="card card-signin my-5">
				<ul class="nav nav-tabs">
					<li class="nav-item"><a class="nav-link ${tag eq '01' ? 'active' :  ''}" href="Board5loginui.do?tag=01">회원</a></li>
					<li class="nav-item"><a class="nav-link ${tag eq '00' ? 'active' :  ''}" href="Board5loginui.do?tag=00">관리자</a></li>
				</ul>
					<div class="card-body">
						<h5 class="card-title text-center">Sign In</h5>
						<form action="Board5login.do" class="form-signin" method="post">
							<input name="tag" value="${tag }" hidden="true">
							<div class="form-label-group">
								<input name="id" id="inputEmail" class="form-control" placeholder="ID" required autofocus> <label for="inputEmail">ID</label>
							</div>

							<div class="form-label-group">
								<input name="pw" type="password" id="inputPassword" class="form-control" placeholder="Password" required> <label for="inputPassword">Password</label>
							</div>

							<div class="custom-control custom-checkbox mb-3">
								<input type="checkbox" class="custom-control-input" id="customCheck1"> <label class="custom-control-label" for="customCheck1">Remember
									password</label>
							</div>
							<button class="btn btn-lg btn-outline-primary btn-block text-uppercase" type="submit">Sign in</button>

						</form>
					</div>
				</div>
				<a class="btn btn-outline-primary " href="#" onclick="historyBack()">이전</a>
				<a class="btn btn-outline-primary " href="Board5list.do">목록</a>

			</div>
		</div>
	</div>

	<jsp:include page="footer.jsp" />

	<script>
		function historyBack() {
			window.history.back();
		}
		
	</script>
</body>
</html>