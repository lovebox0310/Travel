<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>

<head>
<title>게시판 글쓰기</title>
<meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
</head>

<body>
	<%@ include file="../com/top.jsp"%>
	<%@ include file="../com/navbar.jsp"%>

<div class="container">
<h1><br></h1>
<h2>글쓰기</h2>


<form action="board1insert.do" method="post" enctype="multipart/form-data" onsubmit="return formCheck();">
글쓴이: <input name="writer" id="writer" readonly value="${login.id}"><br> 
제목: <input name="title" id="title"><br>

 <div class="form-group">
      <label for="comment">내용:</label>
      <textarea class="form-control" rows="10" id="comment" name="content"></textarea>
    </div>
    
     첨부파일: <input type="file" name="filename"><br/>
    <button type="submit" class="btn btn-primary" value="등록">등록</button>
  </form>
</div>
</form>

<script>
function formCheck() {
var title = document.forms[0].title.value;     
var writer = document.forms[0].writer.value;
var content = document.forms[0].content.value; 


if (title == null || title == ""){     
    alert('제목을 입력하세요');           
    document.forms[0].title.focus();    
    return false;                      
}
if (writer == null ||  writer  == ""){   
    alert('글쓴이를 입력하세요'); 
    document.forms[0].writer.focus();            
    return false;               
}
 
if (content == null ||  content == ""){
    alert('내용을 입력하세요'); 
    document.forms[0].content.focus();
    return false;
}

}

</script>
</body>
</html>