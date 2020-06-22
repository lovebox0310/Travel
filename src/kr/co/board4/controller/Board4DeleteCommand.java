package kr.co.board4.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.dao.Board4DAO;
import kr.co.domain.Command;
import kr.co.domain.CommandAction;
import kr.co.dto.LoginDTO;

public class Board4DeleteCommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		if (session != null) {
			LoginDTO login = (LoginDTO) session.getAttribute("login");
			if (login == null) {
				return new CommandAction(true, "loginui.do");
			}
		}
		
		String sNum = request.getParameter("num");
		int num = 0;
		if (sNum != null) {
			num = Integer.parseInt(sNum);
		}
		
		Board4DAO dao = new Board4DAO();
		dao.delete(num);
		
		return new CommandAction(false, "board4list.do?curPage=1&location=000&thema=000");
	}

}
