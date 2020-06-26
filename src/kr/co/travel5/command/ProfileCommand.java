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

public class ProfileCommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		if(session != null) {
			LoginDTO login = (LoginDTO) session.getAttribute("login");
			if(login != null) {
				MemberDAO dao = new MemberDAO();
				MemberDTO memberDTO = dao.selectById(new LoginDTO(login.getId(), null));
				
				request.setAttribute("memberDTO", memberDTO);
				return new CommandAction(false, "Board5/profile.jsp");
			}
		}
		
		return new CommandAction(true, "Board5list.do");
	}

}
