package kr.co.board2.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.dao.MemberDAO;
import kr.co.domain.Command;
import kr.co.domain.CommandAction;
import kr.co.dto.LoginDTO;

public class LoginCommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		boolean login = new MemberDAO().login(new LoginDTO(id, pw));
		
		
		if(login) {
			HttpSession session = request.getSession();
			session.setMaxInactiveInterval(60 * 15);
			session.setAttribute("login", new LoginDTO(id, null));
			
			return new CommandAction(true, "board2list.do");
		}
		return new CommandAction(true, "loginui.do");
	}
}