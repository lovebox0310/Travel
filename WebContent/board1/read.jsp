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

<div class="container">
<h2>게시글 보기</h2>
글번호: <input name="num" id="num" readonly value="${dto.num}"><br> 
조회수: <input name="readcnt" id="readcnt" readonly value="${dto.readcnt}"><br> 
글쓴이: <input name="writer" id="writer" readonly value="${dto.writer}"><br> 
제목: <input name="title" id="title" readonly value="${dto.title}"><br> 

 <div class="form-group">
      <label for="comment">내용:</label>
      <textarea style="resize: none ; background-color: white"  class="form-control" rows="10" id="content" name="content" disabled>${dto.content}</textarea>
    </div>
  </form>

 <c:choose>
 <c:when test="${login.id ==dto.writer}">	
 <a href="board1updateui.do?num=${dto.num}">수정</a> | 
 <a href="board1delete.do?num=${dto.num}">삭제</a> | 
 <a href="board1replyui.do?num=${dto.num}">답글</a> | 
 <a href="board1list.do">목록</a>
 </c:when>
 <c:otherwise>
 <a href="board1replyui.do?num=${dto.num}">답글</a> | 
 <a href="board1list.do">목록</a>
</c:otherwise>
</c:choose>
</body>
</html>