package kr.co.board4.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.dao.Board4DAO;
import kr.co.domain.Command;
import kr.co.domain.CommandAction;
import kr.co.dto.Board4DTO;

public class Board4UpdateUICommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sNum = request.getParameter("num");
		int num = 0;
		if (sNum != null) {
			num = Integer.parseInt(sNum);
		}
//		System.out.println("[Board4UpdateUICommand]" + sNum);
		Board4DAO dao = new Board4DAO();
		Board4DTO dto = dao.updateui(num);
//		System.out.println(dto.getLocation());
		request.setAttribute("dto", dto);
		
		return new CommandAction(false, "board4/board4updateui.jsp");
	}

}
