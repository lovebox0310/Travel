package kr.co.board4.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.dao.Board4DAO;
import kr.co.domain.Command;
import kr.co.domain.CommandAction;
import kr.co.dto.Board4DTO;

public class Board4UpdateCommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String sNum = request.getParameter("num");
		String location = request.getParameter("location");
		String thema = request.getParameter("thema");
		String writer = request.getParameter("writer");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		int num = 0;
		if (sNum != null) {
			num = Integer.parseInt(sNum);
		}
		
		Board4DAO dao = new Board4DAO();
		dao.update(new Board4DTO(num, writer, title, content, location, thema, null, null, 0, 0, 0, 0));
		
		System.out.println("update num " + num);
		
		return new CommandAction(false, "board4updateui.do?num="+num);
	}

}
