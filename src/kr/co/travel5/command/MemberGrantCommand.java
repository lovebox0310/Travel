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

public class MemberGrantCommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		HttpSession session = request.getSession(false);
		if(session != null) {
			LoginDTO login = (LoginDTO) session.getAttribute("login");
			if(login.getAuthority().equals("00")) {
				if(!login.getId().equals(id)) {
					System.out.println("권한부여 접근");
					MemberDAO dao = new MemberDAO();
					dao.grantAuthority(id);	
				}
				return new CommandAction(false, "Board5memberListUI.do");
			}
		}
		
		return new CommandAction(true, "Board5list.do");
	}

}
