package kr.co.board6.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.dao.Board6DAO;
import kr.co.domain.Command;
import kr.co.domain.CommandAction;
import kr.co.dto.LoginDTO;

public class Notice_LoginCommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		LoginDTO dto = new LoginDTO(id, null);
		Board6DAO dao = new Board6DAO();
		boolean isLogin;
		isLogin = dao.login(new LoginDTO(id, pw));
		
		if (isLogin) {
			HttpSession session = request.getSession();
			session.setAttribute("login", dto);
			session.setMaxInactiveInterval(600);
			return new CommandAction(true, "board6notice_list.do");
		} else {
			return new CommandAction(true, "board6notice_loginui.do");
		}
	}

}
