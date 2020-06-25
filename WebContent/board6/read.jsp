<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="kr.co.dao.Board6DAO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fm"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${dto.title}</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
</head>
<script>
	function onDownload(num) {
		var o = document.getElementById("ifrm_filedown");
		o.src = "download.do?num=" + num;
	}
</script>

<body>
	<%@ include file="../com/top.jsp"%>
	<%@ include file="../com/navbar.jsp"%>
	<iframe id="ifrm_filedown" style="position: absolute; z-index: 1; visibility: hidden;"></iframe>



	<div class="container" style="margin: 30px auto 30px auto;">


		<h2 style="background-color: rgb(55, 55, 55); color: white;">${dto.title}</h2>
		<p class="far fa-sticky-note">&nbsp;글번호: ${dto.num}&nbsp;&nbsp;조회수: ${dto.readcnt}</p>


		<div class="dropdown">
			<button type="button" class="fas fa-bars btn btn-info dropdown-toggle" data-toggle="dropdown">&nbsp;${dto.writer}님의 게시글 더 보기</button>
			<div class="dropdown-menu">
				<a class="dropdown-item" href="board6asklist.do?id=${dto.id}">${dto.writer}님의 질문 목록</a>
				<div class="dropdown-divider"></div>
				<a class="dropdown-item" href="board6replylist.do?id=${dto.id}">${dto.writer}님의 답변 목록</a>
			</div>
		</div>

		<br>


		<div class="jumbotron" style="background-color: rgb(128, 128, 191); color: white;">


			<h5>
				<strong>작성자 :</strong> ${dto.writer}
			</h5>
			<h5>
				<strong>질문 내용 :</strong>
			</h5>
			<h6>${dto.content}</h6>


			<c:set var="fileName" value="${dto.filename}" />
			<%
				String file = (String) pageContext.getAttribute("fileName");
				String filePath = "upload/" + file;
				int pos = filePath.lastIndexOf(".");
				String fileExt = filePath.substring(pos + 1);
				fileExt = fileExt.toLowerCase();
				request.setAttribute("fileExt", fileExt);
				request.setAttribute("jpg", "jpg");
				request.setAttribute("jpeg", "jpeg");
				request.setAttribute("png", "png");
			%>

			<c:if test="${fileExt eq jpg or jpeg or png}">
				<img alt="${dto.filename}" src="upload/${dto.filename}" class="img-thumbnail">
			</c:if>



			<br> <br>

			<h5>
				<a style="color: white;" href="#" onclick="onDownload('${dto.num}')">
					<strong>파일 :</strong> ${dto.filename}
				</a>
			</h5>



		</div>
		<br>


		<c:choose>

			<c:when test="${dto.id eq param.id}">
				<a class="btn btn-warning" href="board6qnaUpdateui.do?num=${dto.num}&id=${param.id}&writer=${writer}">수정</a>
		|
		<button type="button" class="btn btn-danger" data-toggle="modal" data-target="#myModal">삭제</button>
				<div class="modal fade" id="myModal">
					<div class="modal-dialog modal-sm">
						<div class="modal-content">

							<div class="modal-header">
								<h4 class="modal-title">'${dto.title}' 글 삭제</h4>
								<button type="button" class="close" data-dismiss="modal">&times;</button>
							</div>

							<div class="modal-body">정말 삭제하시겠습니까?</div>

							<div class="modal-footer">
								<a class="btn btn-info" href="board6qnaDelete.do?num=${dto.num}&id=${param.id}">예</a>
								<button type="button" class="btn btn-danger" data-dismiss="modal">아니요</button>
							</div>

						</div>
					</div>
				</div>

		|
		<a class="btn btn-info" href="board6qnalist.do?id=${param.id}">목록</a>

			</c:when>
			<c:otherwise>
				<a class="btn btn-warning" href="board6replyui.do?num=${dto.num}&id=${param.id}">답글 쓰기</a>
		|
		<a class="btn btn-info" href="board6qnalist.do?id=${param.id}">목록</a>
			</c:otherwise>
		</c:choose>








		<br> <br>
		<hr>
		<h5 class="fas fa-pen-fancy">
			<strong> 댓글 남기기</strong>
		</h5>
		<form action="board6insertQnaComment.do" method="post" class="was-validated">
			<input type="hidden" name="id" value="${param.id}"> <input type="hidden" name="num" value="${dto.num}"> <input type="hidden"
				name="writer" value="${writer}">


			<div class="form-group">
				<input type="text" class="form-control" placeholder="내용을 입력하세요." name="content" required>
				<div class="valid-feedback">입력 완료</div>
				<div class="invalid-feedback">정보를 입력해주세요.</div>
			</div>
			<button type="submit" class="btn btn-warning">작성완료</button>
		</form>
		<br>
		<hr>


		<c:set var="num" value="${dto.num}" />
		<%
			int number = (Integer) pageContext.getAttribute("num");
			Board6DAO dao = new Board6DAO();
			int count = dao.countComments(number);
			request.setAttribute("count", count);
		%>

		<h5 class="far fa-copy">
			<strong>댓글 목록 [${count}] </strong>
		</h5>
		<br><br>
		<c:forEach items="${commentlist}" var="commentdto">

			<c:if test="${commentdto.repIndent>0}">
				<h4>
				<svg class="bi bi-arrow-return-right" width="1em" height="1em" viewBox="0 0 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
  <path fill-rule="evenodd" d="M10.146 5.646a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-3 3a.5.5 0 0 1-.708-.708L12.793 9l-2.647-2.646a.5.5 0 0 1 0-.708z"/>
  <path fill-rule="evenodd" d="M3 2.5a.5.5 0 0 0-.5.5v4A2.5 2.5 0 0 0 5 9.5h8.5a.5.5 0 0 0 0-1H5A1.5 1.5 0 0 1 3.5 7V3a.5.5 0 0 0-.5-.5z"/>
</svg>
					<span class="badge badge-pill badge-info ">
					
					
					Re: ${commentdto.orgWriter} </span>
				</h4>
			</c:if>


			<div class="media border p-3">

				<div class="media-body">
					<h4>${commentdto.writer}
						<small><i>${commentdto.writeday}</i></small>
					</h4>
					<p>${commentdto.content}</p>
				</div>
			</div>



			<br>

			<button type="button" class="btn btn-secondary" data-toggle="modal" data-target="#myModal${commentdto.num}">답변 달기</button>
			<div class="modal fade" id="myModal${commentdto.num}">
				<div class="modal-dialog modal-sm">
					<div class="modal-content">
						<div class="modal-header">
							<h4 class="modal-title">답변 달기</h4>
							<button type="button" class="close" data-dismiss="modal">&times;</button>
						</div>

						<form action="board6replyComment.do" method="post" class="was-validated">

							<input type="hidden" name="id" value="${param.id}"> <input type="hidden" name="num" value="${commentdto.num}"> <input
								type="hidden" name="qnanum" value="${commentdto.qnanum}"> <input type="hidden" name="writer" value="${writer}"> <input
								type="hidden" name="orgWriter" value="${commentdto.writer}">
							<div class="modal-body">


								<div class="form-group">
									<label for="content">내용 : </label> <input type="text" class="form-control" placeholder="내용을 입력하세요." name="content" required>
									<div class="valid-feedback">입력 완료</div>
									<div class="invalid-feedback">정보를 입력해주세요.</div>
								</div>
							</div>
							<div class="modal-footer">
								<button type="submit" class="btn btn-info">작성완료</button>
								<button type="button" class="btn btn-danger" data-dismiss="modal">취소</button>
							</div>

						</form>

					</div>
				</div>
			</div>

			<c:if test="${commentdto.id eq param.id}">




				<button type="button" class="btn btn-secondary" data-toggle="modal" data-target="#myModalupdate${commentdto.num}">수정</button>
				<div class="modal fade" id="myModalupdate${commentdto.num}">
					<div class="modal-dialog modal-sm">
						<div class="modal-content">
							<div class="modal-header">
								<h4 class="modal-title">댓글 수정</h4>
								<button type="button" class="close" data-dismiss="modal">&times;</button>
							</div>
							<form action="board6updateComment.do" method="post" class="was-validated">

								<input type="hidden" name="id" value="${param.id}"> <input type="hidden" name="num" value="${commentdto.num}"> <input
									type="hidden" name="qnanum" value="${commentdto.qnanum}"> <input type="hidden" name="writer" value="${writer}">
								<div class="modal-body">

									<div class="form-group">
										<label for="content">내용 : </label> <input type="text" class="form-control" placeholder="내용을 입력하세요." name="content" required>
										<div class="valid-feedback">입력 완료</div>
										<div class="invalid-feedback">정보를 입력해주세요.</div>
									</div>
								</div>
								<div class="modal-footer">
									<button type="submit" class="btn btn-info">수정 완료</button>
									<button type="button" class="btn btn-danger" data-dismiss="modal">취소</button>
								</div>
							</form>
						</div>
					</div>
				</div>







				<button type="button" class="btn btn-secondary" data-toggle="modal" data-target="#myModaldelete${commentdto.num}">삭제</button>
				<div class="modal fade" id="myModaldelete${commentdto.num}">
					<div class="modal-dialog modal-sm">
						<div class="modal-content">
							<div class="modal-header">
								<h4 class="modal-title">댓글 삭제</h4>
								<button type="button" class="close" data-dismiss="modal">&times;</button>
							</div>
							<form action="board6deleteComment.do" method="post" class="was-validated">

								<input type="hidden" name="id" value="${param.id}"> <input type="hidden" name="num" value="${commentdto.num}"> <input
									type="hidden" name="qnanum" value="${commentdto.qnanum}"> <input type="hidden" name="repIndent" value="${commentdto.repIndent}">
								<input type="hidden" name="repRoot" value="${commentdto.repRoot}">
								<div class="modal-body">정말 삭제하시겠습니까?</div>
								<div class="modal-footer">
									<button type="submit" class="btn btn-info">예</button>
									<button type="button" class="btn btn-danger" data-dismiss="modal">아니요</button>
								</div>
							</form>
						</div>
					</div>

				</div>

			</c:if>














			<hr>

		</c:forEach>



	</div>


















	<%@ include file="../com/footer.jsp"%>
</body>
</html>