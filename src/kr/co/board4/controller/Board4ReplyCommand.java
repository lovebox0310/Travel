package kr.co.board4.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.dao.Board4DAO;
import kr.co.domain.Command;
import kr.co.domain.CommandAction;
import kr.co.dto.Board4DTO;

public class Board4ReplyCommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String sNum = request.getParameter("num");
		String writer = request.getParameter("writer");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String location = request.getParameter("location");
		String thema = request.getParameter("thema");
		
		int oreginNum = 0;
		if (sNum != null) {
			oreginNum = Integer.parseInt(sNum);
		}
		
		Board4DAO dao = new Board4DAO();
		Board4DTO dto = new Board4DTO(0, writer, title, content, location, thema, null, 0, 0, 0, 0);
		dao.reply(oreginNum, dto);
		
		return new CommandAction(false, "board4list.do?curPage=1&location=000&thema=000");
	}

}
