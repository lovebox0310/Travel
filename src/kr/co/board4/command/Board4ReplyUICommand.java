package kr.co.board4.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.dao.Board4DAO;
import kr.co.domain.Command;
import kr.co.domain.CommandAction;
import kr.co.dto.Board4DTO;
import kr.co.dto.LoginDTO;

public class Board4ReplyUICommand implements Command {

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
		
		String sNum = request.getParameter("num");
		int num = 0;
		if (sNum != null) {
			num = Integer.parseInt(sNum);
		}

		Board4DAO dao = new Board4DAO();
		Board4DTO dto = dao.updateui(num);

		request.setAttribute("dto", dto);
		
		return new CommandAction(false, "board4/replyui.jsp");
	}

}
