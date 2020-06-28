
<%@page import="org.apache.poi.ss.usermodel.HorizontalAlignment"%>
<%@page import="org.apache.poi.ss.usermodel.FillPatternType"%>
<%@page import="org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined"%>
<%@page import="org.apache.poi.ss.usermodel.BorderStyle"%>
<%@page import="org.apache.poi.ss.usermodel.CellStyle"%>
<%@page import="org.apache.poi.ss.usermodel.Cell"%>
<%@page import="org.apache.poi.ss.usermodel.Row"%>
<%@page import="org.apache.poi.ss.usermodel.Sheet"%>
<%@page import="org.apache.poi.hssf.usermodel.HSSFWorkbook"%>
<%@page import="org.apache.poi.ss.usermodel.Workbook"%>
<%@page import="kr.co.domain.BoardDTO"%>
<%@page import="kr.co.to.PageTO"%>
<%@page import="kr.co.dao.Board4DAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="kr.co.dto.Board4DTO"%>
<%@page import="java.util.List"%>
<%@ page language="java"
	contentType="application/vnd.ms-excel;charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
<body>
	<%
	// 게시판 목록조회

	Board4DAO dao = new Board4DAO();
	PageTO to = dao.page(1);
	
    List<BoardDTO> list = to.getList();

    // 워크북 생성
    Workbook wb = new HSSFWorkbook();
    Sheet sheet = wb.createSheet("게시판");
    Row row = null;
    Cell cell = null;
    int rowNo = 0;

    // 테이블 헤더용 스타일
    CellStyle headStyle = wb.createCellStyle();

    // 가는 경계선을 가집니다.
    headStyle.setBorderTop(BorderStyle.THIN);
    headStyle.setBorderBottom(BorderStyle.THIN);
    headStyle.setBorderLeft(BorderStyle.THIN);
    headStyle.setBorderRight(BorderStyle.THIN);

    // 배경색은 노란색입니다.
    headStyle.setFillForegroundColor(HSSFColorPredefined.YELLOW.getIndex());
    headStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

    // 데이터는 가운데 정렬합니다.
    headStyle.setAlignment(HorizontalAlignment.CENTER);

    // 데이터용 경계 스타일 테두리만 지정
    CellStyle bodyStyle = wb.createCellStyle();
    bodyStyle.setBorderTop(BorderStyle.THIN);
    bodyStyle.setBorderBottom(BorderStyle.THIN);
    bodyStyle.setBorderLeft(BorderStyle.THIN);
    bodyStyle.setBorderRight(BorderStyle.THIN);

    // 헤더 생성
    row = sheet.createRow(rowNo++);
    cell = row.createCell(0);
    cell.setCellStyle(headStyle);
    cell.setCellValue("번호");
    cell = row.createCell(1);
    cell.setCellStyle(headStyle);
    cell.setCellValue("이름");
    cell = row.createCell(2);
    cell.setCellStyle(headStyle);
    cell.setCellValue("제목");

    // 데이터 부분 생성
    for(BoardDTO dto : list) {
    	Board4DTO dto4 = (Board4DTO)dto;
        row = sheet.createRow(rowNo++);
        cell = row.createCell(0);
        cell.setCellStyle(bodyStyle);
        cell.setCellValue(dto4.getNum());
        cell = row.createCell(1);
        cell.setCellStyle(bodyStyle);
        cell.setCellValue(dto4.getLocation());
        cell = row.createCell(2);
        cell.setCellStyle(bodyStyle);
        cell.setCellValue(dto4.getThema());
        cell = row.createCell(3);
        cell.setCellStyle(bodyStyle);
        cell.setCellValue(dto4.getTitle());
        cell = row.createCell(4);
        cell.setCellStyle(bodyStyle);
        cell.setCellValue(dto4.getContent());
        cell = row.createCell(5);
        cell.setCellStyle(bodyStyle);
        cell.setCellValue(dto4.getWriter());
        cell = row.createCell(6);
        cell.setCellStyle(bodyStyle);
        cell.setCellValue(dto4.getWriteday());
        cell = row.createCell(7);
        cell.setCellStyle(bodyStyle);
        cell.setCellValue(dto4.getReadcnt());
    }

    // 컨텐츠 타입과 파일명 지정
    response.setContentType("ms-vnd/excel");
    response.setHeader("Content-Disposition", "attachment;filename=test.xls");

    // 엑셀 출력
    wb.write(response.getOutputStream());
    wb.close();

	%>
</body>
</html>