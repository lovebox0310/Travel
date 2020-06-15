<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>

<head>
<title>회원가입</title>
<%@ include file="./com/head.jsp"%>
<script type="text/javascript">
	document.addEventListener("DOMContentLoaded", function() {

		function validation() {
			if (document.getElementById('id').value == '') {
				alert('ID를 확인해주세요.');
				return false;
			}
			if (document.getElementById('pw').value == '') {
				alert('Password를 확인해주세요.');
				return false;
			}
			if (document.getElementById('name').value == '') {
				alert('Name을 확인해주세요.');
				return false;
			}
			if (document('age').value == '') {
				alert('Age를 확인해주세요.');
				return false;
			}
		}

		function unLookTheTextBox() {
			if (confirm('입력된 사용가능한 ID가 삭제됩니다.')) {
				document.getElementById('idChack').classList.remove('d-none');
				document.getElementById('btnIdCheck').classList
						.remove('d-none');
				document.getElementById('btnNewId').classList.add('d-none');
				document.getElementById('id').value = '';
				document.getElementById('btnNewId').removeEventListener(
						'click', unLookTheTextBox);
				document.getElementById('btnIdCheck').addEventListener('click',
						idChack);
			}
		}

		function lookTheTextBox() {
			alert('사용가능한 ID입니다.');
			document.getElementById('idChack').classList.add('d-none');
			document.getElementById('btnIdCheck').classList.add('d-none');
			document.getElementById('btnNewId').classList.remove('d-none');
			document.getElementById('btnNewId').addEventListener('click',
					unLookTheTextBox);
			document.getElementById('btnIdCheck').removeEventListener('click',
					idChack);
		}

		function idChack() {
			let xhr = new XMLHttpRequest();
			xhr.open('GET',
					'idChack?id=' + document.getElementById('id').value, true);
			console.log('READYSTATE: ', xhr.readyState);

			xhr.onprogress = function() {
				console.log('READYSTATE: ', xhr.readyState);
			}

			xhr.onload = function() {
				console.log('READYSTATE: ', xhr.readyState);
				if (this.status == 200) {
					console.log(this.responseText);
					if (this.responseText === 'true') {
						lookTheTextBox();
					} else {
						alert('입력하신 ID는 사용중인 아이디입니다.');
						document.getElementById('id').value = '';
					}
				} else if (this.status == 404) {
					console.log('Not Found');
				}
			}

			xhr.onerror = function() {
				console.log('Request Error...')
			}

			// xhr.onreadystatechange = function() {
			// 	if(this.readyState == 4 && this.status == 200) {
			// 		console.log(this.responseText)
			// 	}
			// }

			xhr.send();
		}

		document.getElementById('btnIdCheck')
				.addEventListener('click', idChack);
	});
</script>
</head>

<body>
	<%@ include file="./com/top.jsp"%>
	<%@ include file="./com/navbar.jsp"%>

	<div class="container" style="margin: 30px auto 30px auto;">
		<h2>어서오세요!!</h2>
		<p>
			저희 Travel사이트에 가입을 위해 몇가지 입력 할 게 있습니다.
			<code>아래 빈칸을 빠짐없이 입력</code>
			해주시면 가입 할 수 있습니다.
		</p>

		<form action="insert.do" class="was-validated" method="post" onsubmit="return validation()">
			<div class="alert alert-warning alert-dismissible b" id="idChack">
				<button type="button" class="close" data-dismiss="alert">&times;</button>
				<strong>Warning!</strong> 아이디를 확인해주세요.
			</div>
			<div class="form-group">
				<div class="row">
					<div class="col-sm-12">
						<label for="id">Id:</label>
					</div>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="id" placeholder="ID를 입력해주세요" name="id" required>
					</div>
					<div class="col-sm-2 text-center">
						<a class="btn btn-secondary d-none" id="btnNewId" style="color: white;">다른ID입력</a>
						<a class="btn btn-secondary" id="btnIdCheck" style="color: white;">ID중복확인</a>
					</div>
				</div>
				<div class="valid-feedback">완료됨.</div>
				<div class="invalid-feedback">정보를 입력해주세요.</div>
			</div>
			<div class="form-group">
				<label for="pw">Password:</label> <input type="password" class="form-control" id="pw" placeholder="Password를 입력해주세요" name="pw" required>
				<div class="valid-feedback">완료됨.</div>
				<div class="invalid-feedback">정보를 입력해주세요.</div>
			</div>
			<div class="form-group">
				<label for="name">Name:</label> <input type="text" class="form-control" id="name" placeholder="이름을 입력해주세요" name="name" required>
				<div class="valid-feedback">완료됨.</div>
				<div class="invalid-feedback">정보를 입력해주세요.</div>
			</div>
			<div class="form-group">
				<label for="age">Age:</label> <input type="text" class="form-control" id="age" placeholder="나이를 입력해주세요" name="age" required>
				<div class="valid-feedback">완료됨.</div>
				<div class="invalid-feedback">정보를 입력해주세요.</div>
			</div>
			<div class="form-group form-check">
				<label class="form-check-label"> <input class="form-check-input" type="checkbox" name="remember" required> 정보제공에 동의 합니다.
					<div class="valid-feedback">완료됨.</div>
					<div class="invalid-feedback">정보를 입력해주세요.</div>
				</label>

			</div>
			<button type="submit" class="btn btn-primary">회원가입</button>
		</form>
	</div>
	<%@ include file="./com/footer.jsp"%>
</body>

</html>