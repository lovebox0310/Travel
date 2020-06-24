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

public class MemberRevokeCommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session != null) {
			Board5loginDTO login = (Board5loginDTO) session.getAttribute("login");
			if(login.getAuthority().equals("00")) {
				String id = request.getParameter("id");
				if(!login.getId().equals(id)) {
					System.out.println("권한뺏기 접근");
					MemberDAO dao = new MemberDAO();
					dao.revokeAuthority(id);	
				}
				return new CommandAction(false, "Board5memberListUI.do");
			}
		}
		
		return new CommandAction(true, "Board5list.do");
	}

}
