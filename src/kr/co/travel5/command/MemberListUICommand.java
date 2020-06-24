package kr.co.travel5.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.dao.MemberDAO;
import kr.co.domain.Command;
import kr.co.domain.CommandAction;
import kr.co.dto.*;

public class MemberListUICommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session != null) {
			Board5loginDTO login = (Board5loginDTO) session.getAttribute("login");
			if(login != null) {
				if(login.getAuthority().equals("00")) {
					
					List<MemberDTO> list = new MemberDAO().readAll();
					request.setAttribute("list", list);
					
					return new CommandAction(false, "Board5/memberList.jsp");
				}
			}
		}
		return new CommandAction(true, "Board5list.do");
	}

}
