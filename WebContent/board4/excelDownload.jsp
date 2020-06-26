<%@ page language="java" contentType="application/vnd.ms-excel;charset=UTF-8" pageEncoding="UTF-8"%>

<%
    response.setHeader("Content-Disposition","attachment;filename=member.xls");    //디폴트 파일명 지정
    response.setHeader("Content-Description", "JSP Generated Data"); 
%>
<!DOCTYPE html>
<html lang="ko">
   <body>
      ${excelResult.fe_html}
   </body>
</html>