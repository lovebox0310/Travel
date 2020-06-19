package kr.co.controller;

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
		
		MemberDAO dao = new MemberDAO();
		boolean isLogin = dao.login(new LoginDTO(id, pw));
		
		if (isLogin) {
			HttpSession session = request.getSession();
			session.setAttribute("login", new LoginDTO(id, null));
			session.setMaxInactiveInterval(10);
			return new CommandAction(false, "main.jsp");
		} else {
			request.setAttribute("error", "ID와 Password가 일치하지 않습니다.");
			return new CommandAction(false, "loginui.do");
		}
	}

}
