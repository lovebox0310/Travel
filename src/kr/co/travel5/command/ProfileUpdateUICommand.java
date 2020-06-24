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

public class ProfileUpdateUICommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session != null) {
			Board5loginDTO login = (Board5loginDTO) session.getAttribute("login");
			if(login != null) {
				MemberDAO dao = new MemberDAO();
				MemberDTO memberDTO = dao.read(login.getId());
				
				request.setAttribute("memberDTO", memberDTO);
				return new CommandAction(false, "Board5/profileUpdate.jsp");
			}
		}
		
		return new CommandAction(true, "Board5list.do");
	}

}
