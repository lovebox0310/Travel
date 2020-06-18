package kr.co.board4.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.dao.Board4DAO;
import kr.co.domain.Command;
import kr.co.domain.CommandAction;
import kr.co.dto.Board4DTO;

public class Board4InsertCommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String writer = request.getParameter("writer");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String location = request.getParameter("location");
		String thema = request.getParameter("thema");

		
		
		Board4DTO dto = new Board4DTO(-1, writer, title, content, location, thema, null, 0, 0, 0, 0);
		Board4DAO dao = new Board4DAO();
		dao.insert(dto);
		
		return new CommandAction(true, "board4list.do?curPage=1&location=000&thema=000");
	}

}
