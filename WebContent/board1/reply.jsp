<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css"
	integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="../com/top.jsp"%>
	<%@ include file="../com/navbar.jsp"%>
	<div class="container" style="margin: 30px auto">
<h2>댓글 쓰기</h2>
<% String num = request.getParameter("num"); %>

<form action="board1reply.do" method="post">
<input type="hidden" name="num" value="${param.num}">
글쓴이: <input name="writer" id="writer" readonly value="${login.id}"><br> 
제목: <input name="title"><br>
<div class="form-group">
      <label for="comment">내용:</label>
      <textarea class="form-control" rows="10" id="comment" name="content"></textarea>
    </div>
     <div class="form-group">
      <input type="file" class="form-control-file border" name="file">
    </div>
    <button type="submit" class="btn btn-primary" value="등록">등록</button>
  </form>
</div>
</form>
</body>
</html>