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
<h2>게시물 목록</h2>  
<p>이곳은 교통 정보를 공유하는 게시판입니다.</p>

<div class="container">
<c:choose>
	<c:when test="${empty login}">
<button type="button" class="btn btn-outline-primary" data-toggle="modal" data-target="#myModal">글쓰기</button>
	<div class="modal fade" id="myModal">
    <div class="modal-dialog">
      <div class="modal-content">
      <div class="modal-header">
          <h4 class="modal-title">로그인이 되어있지 않습니다.</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
           </div>
           <div class="modal-body">
          로그인 후 이용해 주세요.
        </div>
        <div class="modal-footer">
        <a href="loginui.do" button type="button" class="btn btn-danger">로그인</a></button>
         <button type="button" class="btn btn-danger" data-dismiss="modal">창닫기</button>
        </div>
 </div>
</div>
</div>
</div>
</div>

</c:when>
<c:otherwise>
<a href="board1insertui.do" button type="button" class="btn btn-outline-primary">글쓰기</a></button>
</c:otherwise>
</c:choose>
  
<table class="table table-hover">	

            <thead>
            <tr>
                <th>번호</th>
				<th>제목</th>
				<th>글쓴이</th>
				<th>날짜</th>
				<th>조회수</th> 
			
			</tr>
			</thead>
			
			<tbody>
			 <c:forEach items="${list}" var="dto">
			  <tr>
			    <td>${dto.num}</td>
			    
			    <td width="300px">
			   <c:forEach begin="1" end="${dto.repIndent}">
						&#8627;
					</c:forEach>
					<a href="board1read.do?num=${dto.num}">${dto.title}</a>
					</td>
					
					<td>${dto.writer}</td>
					<td>${dto.writeday}</td>
					<td>${dto.readcnt}</td>
			
				</tr>
			</c:forEach>
			
		</tbody>

	</table>
	<a href="board1list.do?curPage=${(to.curPage-1) > 0 ? (to.curPage-1) : 1}">&laquo;</a>&nbsp;&nbsp;

	<c:forEach begin="${to.beginPageNum}" end="${to.stopPageNum}" var="idx">
	
	<c:if test="${to.curPage == idx}">
	<a style="font-size: 20px;" href="board1list.do?curPage=${idx}">${idx}</a>&nbsp;&nbsp;
	</c:if>
	<c:if test="${to.curPage != idx}">
	<a href="board1list.do?curPage=${idx}">${idx}</a>&nbsp;&nbsp;
	</c:if>
	</c:forEach>
    <a href="board1list.do?curPage=${(to.curPage+1) < to.totalPage ? (to.curPage+1) : to.totalPage }">&raquo;</a>
 
</body>
</html>