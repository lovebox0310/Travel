package kr.co.board4.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.domain.Command;
import kr.co.domain.CommandAction;
import kr.co.dto.LoginDTO;

public class Board4InsertUICommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		if (session == null) {
			return new CommandAction(true, "loginui.do");
		} else {
			LoginDTO login = (LoginDTO) session.getAttribute("login");
			if (login == null) {
				return new CommandAction(true, "loginui.do");
			}
		}
		
		return new CommandAction(false, "board4/board4insertui.jsp");
	}

}
