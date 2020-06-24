<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
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
		<h2>회원 가입</h2>
		<p>어서오세요, 저희 사이트의 가입을 환영합니다.</p>
		<form action="Board5signup.do" class="was-validated">
			<div class="form-group">
				<label for="id">ID:</label> 
				<input type="text" class="form-control" id="id" placeholder="Enter id" name="id" maxlength="6" required>
				<div class="valid-feedback idcheck"><p>입력됨</p></div>
				<div class="invalid-feedback idcheck"><p>1~6자의 영문 소문자, 숫자와 특수기호(_),(-)만 사용 가능합니다.</p></div>
			</div>
			<div class="form-group">
				<label for="pw">Password:</label> 
				<input type="password" class="form-control" id="pw" placeholder="암호를 입력하세요" name="pw" maxlength="20" required>
				<div class="valid-feedback">입력됨.</div>
				<div class="invalid-feedback">1~20자 영문 대 소문자, 숫자, 특수문자를 사용하세요.</div>
			</div>
			<div class="form-group">
				<label for="name">Name:</label> <input type="text" class="form-control" id="name" placeholder="이름을 입력하세요" name="name" required>
				<div class="valid-feedback">입력됨.</div>
				<div class="invalid-feedback">한글과 영문 대 소문자를 사용하세요. (특수기호, 공백 사용 불가)</div>
			</div>
			<div class="form-group">
				<label for="age">Age:</label> <input type="number" class="form-control" id="age" placeholder="나이를 입력하세요" name="age" required>
				<div class="valid-feedback">입력됨.</div>
				<div class="invalid-feedback">생년월일을 다시 확인해주세요.</div>
			</div>
			<div class="form-group form-check">
				<label class="form-check-label"> <input class="form-check-input" type="checkbox" name="remember" required> 
				나는 이사이트에 정보입력을 동의 합니다.
					<div class="valid-feedback">입력됨.</div>
					<div class="invalid-feedback">계속하려면 이 확인란을 선택하십시오.</div>
				</label>
			</div>
			<button type="submit" class="btn btn-outline-primary">회원 가입</button>
			<a class="btn btn-outline-primary" href="Board5list.do">목록</a>
		</form>
	</div>
	<jsp:include page="footer.jsp" />


	<script type="text/javascript">
		$(document).ready(function() {
			$("input").focusout(function(event) {
				var id = $("#id").val();
				
				$.ajax({
					type : "get",
					url : "idchecker",
					data : {
						id : id
					},
					dataType : "text",
					success : function(result) {
						if (result == "성공") {
							$(".idcheck p").text('사용 가능한 아이디 입니다.')
						} else {
							$(".idcheck p").text('이미 사용중이거나 탈퇴한 아이디입니다.')
							$("#id").val('');
						}	
					}
				});
			})
		})
	</script>

</body>
</html>