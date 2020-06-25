package kr.co.travel5.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.dao.MemberDAO;
import kr.co.domain.Command;
import kr.co.domain.CommandAction;
import kr.co.dto.*;

public class ProfileDeleteCommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session != null) {
			LoginDTO login = (LoginDTO) session.getAttribute("login");
			if(login != null) {
				String id = login.getId();
				new MemberDAO().delete(new LoginDTO(id, null));
				session.invalidate();
			}
		}
		return new CommandAction(true, "Board5list.do");
	}

}
